package com.najiujiangzi.jiangzi.util;

public class Page {
    //数据库开始行数（当前页数 - 1） * 每页数量
    private Integer startPage = 0;
    //每页数量
    private Integer pageSize = 20;
    //当前页数
    private Integer nowPage = null;

    public void setNowPage(Integer nowPage) {
        this.startPage = (nowPage - 1) * pageSize;
        this.nowPage = nowPage;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        if (nowPage != null) {
            startPage = (nowPage - 1) * pageSize;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getNowPage() {
        return nowPage;
    }
}
