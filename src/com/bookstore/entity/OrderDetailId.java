package com.bookstore.entity;
// Generated May 22, 2018 5:46:15 AM by Hibernate Tools 5.2.10.Final

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * OrderDetailId generated by hbm2java
 */
@Embeddable
public class OrderDetailId implements java.io.Serializable {
 
    private Book book;
    private BookOrder bookOrder;
     
    public OrderDetailId() {
    }
 
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", insertable = false, updatable = false, nullable = false)
    public Book getBook() {
        return this.book;
    }
 
    public void setBook(Book book) {
        this.book = book;
    }
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false)
    public BookOrder getBookOrder() {
        return this.bookOrder;
    }
 
    public void setBookOrder(BookOrder bookOrder) {
        this.bookOrder = bookOrder;
    }
}