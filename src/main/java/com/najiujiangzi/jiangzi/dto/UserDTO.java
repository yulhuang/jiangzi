package com.najiujiangzi.jiangzi.dto;

import com.najiujiangzi.jiangzi.model.User;

public class UserDTO extends User {
    private String likeFind;

    public String getLikeFind() {
        return likeFind;
    }

    public void setLikeFind(String likeFind) {
        this.likeFind = likeFind;
    }
}
