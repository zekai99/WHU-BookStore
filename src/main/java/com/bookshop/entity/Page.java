package com.bookshop.entity;


import com.bookshop.util.configs;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @ClassName: Page
 * @Description: 用于传输分页信息
 * @Author: 曾志昊
 * @Date: 2020/3/28 20:08
 */

public class Page<T> {
    private Integer startPage = 1;//当前页数
    private Integer pageSize ;//每页显示的记录数
    private Integer totalCount;//总记录数
    private Integer totalPage;//总页数
    private List<T> lists;//每页的显示的数据
    public Page() {
        super();
        startPage = 1;
        pageSize = configs.pageSize;
    }

    public Page(Integer pageSize){
        this.pageSize = pageSize;
    }

    public int getCurrPage() {
        return startPage;
    }

    public void setCurrPage(Integer startPage) {
        this.startPage = startPage == null?this.startPage:startPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {

        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getLists() {
        return lists;
    }

    public void setLists(List<T> lists) {
        this.lists = lists;
    }

}
