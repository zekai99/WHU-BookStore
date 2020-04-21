package com.bookshop.controller;

import com.bookshop.common.checkSession;
import com.bookshop.common.responseFromServer;
import com.bookshop.entity.Book;
import com.bookshop.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BookController
 * @Description:
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:48
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Resource
    BookService bookService;

    @RequestMapping("/getBookById")
    @ResponseBody
    public responseFromServer getBookById(@RequestBody Integer bookId) {
        return bookService.getBook(bookId);
    }

    @RequestMapping("/getBook")
    @ResponseBody
    public responseFromServer getBook(@RequestBody Book book) {
        return bookService.getBook(book.getBookId());
    }

    @RequestMapping("/searchBooksPage")
    @ResponseBody
    public responseFromServer searchBooksPage(@RequestBody Map<String, Object> requestMap) {
        return bookService.searchBooksPage(requestMap);
    }

    /*书本推荐暂时先返回所有的书籍*/
    @RequestMapping("/getRecommendedBooks")
    @ResponseBody
    public responseFromServer getRecommendedBooks(@RequestBody Map<String, Object> requestMap, HttpSession session) {
        return searchBooksPage(requestMap);
    }

    @RequestMapping("/getAllBooksPage")
    @ResponseBody
    public responseFromServer getAllBooksPage(@RequestBody Map<String, Object> queryMap, HttpSession session) {
        if (checkSession.checkManager(session))
            return bookService.getAllBooksPage(queryMap);
        else return responseFromServer.needLogin();
    }


    @RequestMapping("/insertBook")
    @ResponseBody
    public responseFromServer insertBook(@RequestBody Book book, HttpSession session) {
        if (checkSession.checkManager(session))
            return bookService.insertBook(book);
        else return responseFromServer.needLogin();
    }

    @RequestMapping("/updateBook")
    @ResponseBody
    public responseFromServer updateBook(@RequestBody Book book, HttpSession session) {
        if (checkSession.checkManager(session))
            return bookService.updateBook(book);
        else return responseFromServer.needLogin();

    }

    @RequestMapping("/updateBooks")
    @ResponseBody
    public responseFromServer updateBooks(@RequestBody List<Book> books, HttpSession session) {
        if (checkSession.checkManager(session)) {
            return bookService.updateBooks(books);
        } else {
            return responseFromServer.needLogin();
        }
    }


    @RequestMapping("/deleteBookById")
    @ResponseBody
    public responseFromServer deleteBookById(@RequestBody Integer bookId, HttpSession session) {
        if (checkSession.checkManager(session))
            return bookService.deleteBookById(bookId);
        else
            return responseFromServer.needLogin();
    }


    @RequestMapping("/deleteBook")
    @ResponseBody
    public responseFromServer deteleBook(@RequestBody Book book, HttpSession session) {
        if (checkSession.checkManager(session)) {
            return bookService.deleteBook(book);
        } else {
            return responseFromServer.needLogin();
        }
    }

    @RequestMapping("/deleteBooks")
    @ResponseBody
    public responseFromServer deleteBooks(@RequestBody List<Book> books, HttpSession session) {
        if (checkSession.checkManager(session))
            return bookService.deleteBooks(books);
        else
            return responseFromServer.needLogin();
    }

}
