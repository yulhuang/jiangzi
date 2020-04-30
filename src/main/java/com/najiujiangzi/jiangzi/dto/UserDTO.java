package com.najiujiangzi.jiangzi.dto;

import com.najiujiangzi.jiangzi.model.Role;
import com.najiujiangzi.jiangzi.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDTO extends User implements UserDetails {
    private String likeFind;
    private List<Role> roles = new ArrayList<>();

    public UserDTO() {
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getLikeFind() {
        return likeFind;
    }

    public void setLikeFind(String likeFind) {
        this.likeFind = likeFind;
    }

    //该用户拥有的角色
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roleList = new ArrayList<>();
        for (Role role : roles) {
            roleList.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return roleList;
    }

    //密码
    public String getPassword() {
        return super.getPassword();
    }

    //用户名
    @Override
    public String getUsername() {
        return super.getName();
    }

    //是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //密码是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否可用
    @Override
    public boolean isEnabled() {
        return super.getDeleted() == null || !super.getDeleted();
    }
}
