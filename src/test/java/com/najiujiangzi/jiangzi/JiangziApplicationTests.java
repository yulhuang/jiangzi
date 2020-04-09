package com.najiujiangzi.jiangzi;

import com.najiujiangzi.jiangzi.dto.UserDTO;
import com.najiujiangzi.jiangzi.mappers.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JiangziApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        UserDTO dto = new UserDTO();
        dto.setLikeFind("se");
        userMapper.find(dto).forEach(System.out::println);
    }

}
