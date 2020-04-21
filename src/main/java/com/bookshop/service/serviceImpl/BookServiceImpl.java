package com.bookshop.service.serviceImpl;

import com.bookshop.common.responseFromServer;
import com.bookshop.dao.BookDao;
import com.bookshop.entity.Book;
import com.bookshop.service.BookService;
import com.bookshop.entity.Page;
import com.bookshop.util.configs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BookServiceImplementation
 * @Description:
 * @Author: 曾志昊
 * @Date: 2020/3/30 16:28
 */
@Service(value = "BookService")
public class BookServiceImpl implements BookService {

    BookDao bookDao;

    public responseFromServer getBook(Integer bookId){
        Book book = bookDao.getBook(bookId);
        if(book!=null){
            return responseFromServer.success(book);
        }
        return responseFromServer.error();

    }

    public responseFromServer getAllBooks(){
        List<Book> allBooks = bookDao.getAllBooks();
        return allBooks==null?
                responseFromServer.error()
                :
                responseFromServer.success(allBooks);
    }


    private Page<Book> getPage(Map<String,Object> queryMap){
        Page<Book> page = new Page<Book>(configs.pageSize);
        Integer startPage = (Integer)(queryMap.get("startPage"));
        queryMap.put("startPage",(startPage-1)*configs.pageSize);
        queryMap.put("pageSize",configs.pageSize);
        page.setCurrPage(startPage);
        page.setTotalCount(bookDao.count(queryMap));
        page.setTotalPage(((Double)Math.ceil((double)page.getTotalCount()/(double)configs.pageSize)).intValue());
        page.setLists(bookDao.searchBooks(queryMap));
        return page;
    }

    public responseFromServer getAllBooksPage(Map<String,Object> requestMap){
        if(requestMap.get("startPage")==null){
            requestMap.put("startPage",1);
        }
        return responseFromServer.success(getPage(requestMap));
    }

    public responseFromServer searchBooksPage(Map<String,Object> requestMap){
        if(requestMap.get("startPage")==null){
            requestMap.put("startPage",1);
        }
        return responseFromServer.success(getPage(requestMap));
    }

    public responseFromServer insertBook(Book book){
        if(bookDao.insertBook(book)==1){
            return responseFromServer.success(book);
        }else{
            return responseFromServer.error();
        }
    }


    /*TODO*/
    public responseFromServer insertBooks(List<Book> books){
        if(books==null||books.size()==0)
            return responseFromServer.error("待插入书本列表为空");
        int rows = 0;
        for(Book book:books){
            if(bookDao.insertBook(book)==1){
                rows++;
            }
        }
        if(rows<books.size()){
            return responseFromServer.error(String.format("插入了%d(/%d)",rows,books.size()));
        }
        return responseFromServer.success();
    }

    @Transactional
    public responseFromServer updateBook(Book book){
        if(book.getBookId()==null)
            return responseFromServer.error("书本信息有误");
        if(bookDao.updateBook(book)==1){
            return responseFromServer.success();
        }else{
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return responseFromServer.error("插入书籍失败");
        }
    }

    public responseFromServer updateBooks(List<Book> books){
        if(books==null||books.size()==0){
            return responseFromServer.error("待更新书本列表为空");
        }
        int rows = 0;
        for(Book book:books){
            if(updateBook(book).isSuccess()){
                rows++;
            }
        }
        if(rows<books.size()){
            return responseFromServer.error(String.format("更新了%d(/%d)",rows,books.size()));
        }
        return responseFromServer.success("成功更新所有书籍");


//        return bookDao.updateBooks(books);
    }

    public responseFromServer deleteBookById(Integer bookId){
        if(bookDao.deleteBookById(bookId)==1){
            return responseFromServer.success("成功删除");
        }else{
            return responseFromServer.error("删除书籍失败");
        }
    }


    public responseFromServer deleteBook(Book book){
        if(bookDao.deleteBook(book)==1){
            return responseFromServer.success("成功删除");
        }else{
            return responseFromServer.error("删除书籍失败");
        }
    }

    public responseFromServer deleteBooks(List<Book> books){
        if(books==null||books.size()==0){
            return responseFromServer.error("待更新书本列表为空");
        }
        int rows = 0;
        for(Book book:books){
            if(deleteBook(book).isSuccess()){
                rows++;
            }
        }
        if(rows<books.size()){
            return responseFromServer.error(String.format("更新了%d(/%d)",rows,books.size()));
        }
        return responseFromServer.success("成功更新所有书籍");
    }


    @Autowired
    public BookServiceImpl(BookDao bookDao){
        this.bookDao = bookDao;
    }
}
