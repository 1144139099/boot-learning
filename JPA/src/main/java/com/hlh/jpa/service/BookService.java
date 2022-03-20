package com.hlh.jpa.service;

import com.hlh.jpa.entity.Book;
import org.springframework.data.domain.Page;

public interface BookService {

    Page<Book> findBookNoCriteria(Integer page,Integer size);

    Page<Book> findBookCriteria(Integer page,Integer size,Book book);
}
