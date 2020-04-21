package com.bookshop.entity;

import java.util.HashMap;
import java.util.Map;

import static com.bookshop.util.Util.checkStringIsEmpty;

/**
 * @ClassName: Book
 * @Description: 书籍实体类
 * @Author: 曾志昊
 * @Date: 2020/3/28 1:55
 */
public class Book {
    private Integer bookId;
    private Double price;
    private String bookName;
    private String detail;
    private String bookType;
    private String image;
    private String publishTime;
    private String bookStatus;
    private String author;
    private String publisher;

/*    public static void main(String[] args) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("startPage","1");
        Integer startPage = (Integer)(queryMap.get("startPage"));
        System.out.printf(startPage+"");
    }*/

    public HashMap<String,Object> toMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("bookId",bookId==null?null:bookId+"");
        map.put("price",price==null?null:price+"");
        map.put("bookName",checkStringIsEmpty(bookName));
        map.put("detail",checkStringIsEmpty(detail));
        map.put("bookType",checkStringIsEmpty(bookType));
        map.put("image",checkStringIsEmpty(image));
        map.put("publishTime",checkStringIsEmpty(publishTime));
        map.put("bookStatus",checkStringIsEmpty(bookStatus));
        return map;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Book(Integer bookId, Double price, String bookName, String detail, String bookType, String image, String publishTime, String bookStatus, String author, String publisher) {
        this.bookId = bookId;
        this.price = price;
        this.bookName = bookName;
        this.detail = detail;
        this.bookType = bookType;
        this.image = image;
        this.publishTime = publishTime;
        this.bookStatus = bookStatus;
        this.author = author;
        this.publisher = publisher;
    }

    public Book() {
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
