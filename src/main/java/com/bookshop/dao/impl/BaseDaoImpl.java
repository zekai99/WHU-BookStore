package com.bookshop.dao.impl;

import com.bookshop.dao.BaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

    @Autowired
    public void setSqlsessionFactory(SqlSessionFactory sqlsessionFactory){
        super.setSqlSessionFactory(sqlsessionFactory);
    }

    private String namespace;//命名空间

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public List<T> find(Map map){
        List<T> oList = this.getSqlSession().selectList(namespace+".find",map);
        return oList;
    }

    public T get(Integer id){
        return this.getSqlSession().selectOne(namespace+".get",id);
    }

    public void insert(T entity){
        this.getSqlSession().insert(namespace+".insert",entity);
    }

    public void update(T entity){
        this.getSqlSession().update(namespace+".update",entity);
    }

    public void deleteById(Serializable id){
        this.getSqlSession().delete(namespace+".deleteById",id);
    }

    public void delete(Serializable[] ids){
        this.getSqlSession().delete(namespace+".delete",ids);
    }

}
