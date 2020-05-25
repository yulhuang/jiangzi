package com.najiujiangzi.jiangzi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.najiujiangzi.jiangzi.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
//开启接口上的注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder())
        auth.authenticationProvider(authenticationProvider());
    }

    public static BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //主页接口和登录接口谁都可以访问
                .antMatchers("/home/**", "/login/**").permitAll()
                .antMatchers("/login/test").hasRole("ADMIN")
                // 其余所有路径都要求用户登陆
                .anyRequest().authenticated()
                .and()
                //开启登录功能
                .formLogin()
                //未登录时跳转页面
                .loginPage("/login/notLogin")
                //登录表单提交接口
                .loginProcessingUrl("/login")
                //登录成功后跳转路径
                .successForwardUrl("/login/success").permitAll()
                //登录失败会跳转路径
//                .failureForwardUrl("/index/loginError");
//                 自定义登录失败处理类,返回json
                .failureHandler((request, response, ex) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    PrintWriter out = response.getWriter();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("statusCode", 401);
                    if (ex instanceof UsernameNotFoundException) {
                        map.put("message", "账号错误");
                    } else if (ex instanceof BadCredentialsException) {
                        map.put("message", "密码错误");
                    } else if (ex instanceof DisabledException) {
                        map.put("message", "账户被禁用");
                    } else if (ex instanceof LockedException) {
                        map.put("message", "账户已被锁定");
                    } else if (ex instanceof AccountExpiredException) {
                        map.put("message", "账户已过期");
                    } else if (ex instanceof CredentialsExpiredException) {
                        map.put("message", "凭证已过期");
                    } else {
                        map.put("message", "登录失败!");
                    }
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                .and()
                .exceptionHandling()
                //没权限时访问的路径，返回json
                .accessDeniedPage("/login/noRights");

        http.logout().logoutSuccessUrl("/login/out")
                //关闭跨站请求伪造，不然/logout会404
                .and().csrf().disable();

        //自动登录功能
        http.rememberMe().tokenRepository(persistentTokenRepository()).userDetailsService(myUserDetailService)
        // 有效时间：单位s
                .tokenValiditySeconds(86400);
        //指定前台传递的是否rememberme的参数名,前台要传递的参数值是true或false
//        http.rememberMe().rememberMeParameter("rememberMe").rememberMeServices(persistentTokenRepository());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(myUserDetailService);
        //为true时用户名是否错误都会抛BadCredentialsException异常，为false时用户名错误抛UsernameNotFoundException异常而密码错误抛出BadCredentialsException异常
        authenticationProvider.setHideUserNotFoundExceptions(false);
        authenticationProvider.setPasswordEncoder(getEncoder());
        return authenticationProvider;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        //配置数据源
        tokenRepository.setDataSource(dataSource);
        // 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

}
