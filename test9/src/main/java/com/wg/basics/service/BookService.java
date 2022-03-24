package com.wg.basics.service;

import com.wg.basics.entity.Book;
import com.wg.basics.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookService{

    final BookMapper bookMapper;
    @Autowired
    public BookService (BookMapper bookMapper){
       this.bookMapper=bookMapper;
   }




    public int addBook(Book book){
            return bookMapper.addBook(book);
    }

    public int deleteBook(Integer id){
        return bookMapper.deleteBook(id);
    }

    public int updateBook(Book book){
        return bookMapper.updateBook(book);
    }

    public Book getBookById(Integer id){
        return bookMapper.getBookById(id);
    }

    public List<Book> getBooksAll(){
        return bookMapper.getBooksAll();
    }
}
