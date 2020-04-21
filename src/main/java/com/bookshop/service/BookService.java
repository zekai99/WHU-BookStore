package com.bookshop.service;

import com.bookshop.common.responseFromServer;
import com.bookshop.entity.Book;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: BookService
 * @Description:
 * @Author: 曾志昊
 * @Date: 2020/3/28 2:00
 */
public interface BookService {
    public responseFromServer getBook(Integer bookId);//只查询一个数据，常用于修改
    public responseFromServer getAllBooksPage(Map<String,Object> queryMap);

    public responseFromServer searchBooksPage(Map<String,Object> queryMap);//根据条件查询多个结果

    public responseFromServer insertBook(Book book);//插入，用实体作为参数
    public responseFromServer insertBooks(List<Book> books);//插入，用实体作为参数

    public responseFromServer updateBook(Book book);//修改，用实体作为参数
    public responseFromServer updateBooks(List<Book> books);

    public responseFromServer deleteBookById(Integer bookId);//按id删除 删除一条 支持整型和字符串类型id
    public responseFromServer deleteBook(Book book);
    public responseFromServer deleteBooks(List<Book> bookIds);//批量删除 支持整型和字符串类型id

}
