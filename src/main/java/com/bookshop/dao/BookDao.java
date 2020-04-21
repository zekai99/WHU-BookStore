package com.bookshop.dao;

import com.bookshop.entity.Book;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: BookDao
 * @Description: TODO
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:50
 */

public interface BookDao{
    Book getBook(Integer bookId);//只查询一个数据，常用于修改
    List<Book> getAllBooksPage(Map map);
    List<Book> getAllBooks();

    List<Book> searchBooks(Map map);//根据条件查询多个结果

    Integer insertBook(Book book);//插入，用实体作为参数
    //Integer insertBooks(List<Book> books);//插入，用实体作为参数

    Integer updateBook(Book book);//修改，用实体作为参数
    Integer updateBooks(List<Book> books);

    Integer count(Map map);

    Integer deleteBookById(Integer bookId);//按id删除 删除一条 支持整型和字符串类型id
    Integer deleteBook(Book book);
    Integer deleteBooks(List<Book> bookIds);//批量删除 支持整型和字符串类型id
}
