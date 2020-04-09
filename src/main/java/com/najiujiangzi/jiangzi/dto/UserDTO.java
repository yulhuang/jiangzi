package com.najiujiangzi.jiangzi.dto;

import com.najiujiangzi.jiangzi.model.User;
import lombok.Data;

@Data
public class UserDTO extends User {
    private String likeFind;
}
