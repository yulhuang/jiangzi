package com.najiujiangzi.jiangzi;

import com.najiujiangzi.jiangzi.dto.UserDTO;
import com.najiujiangzi.jiangzi.service.UserService;
import com.najiujiangzi.jiangzi.util.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JiangziApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        UserDTO dto = new UserDTO();
//        dto.setLikeFind("se");
        Page page = new Page();
        page.setNowPage(1);
        page.setPageSize(2);
//        userService.find(dto, null).forEach(System.out::println);
        System.out.println(userService.findOne(dto));
    }

}
