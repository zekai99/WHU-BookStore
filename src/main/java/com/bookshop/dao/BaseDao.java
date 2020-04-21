package com.bookshop.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
    T get(Integer id);//只查询一个数据，常用于修改
    List<T> find(Map map);//根据条件查询多个结果
    void insert(T entity);//插入，用实体作为参数
    void update(T entity);//修改，用实体作为参数
    void deleteById(Serializable id);//按id删除 删除一条 支持整型和字符串类型id
    void delete(Serializable[] ids);//批量删除 支持整型和字符串类型id
}
