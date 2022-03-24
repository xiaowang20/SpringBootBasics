package com.wg.basics.controller;

import com.wg.basics.entity.Book;
import com.wg.basics.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/getBookOps")
    public void getBookOps(){
        Book book1 = new Book();
        book1.setName("西游记");
        book1.setAuthor("吴承恩");
        int i = bookService.addBook(book1);
        System.out.println("addBook" + i);
        System.out.println("==============");
        Book book2 = new Book();
        book2.setId(1);
        book2.setName("水浒传");
        book2.setAuthor("施耐庵");
        int i1 = bookService.updateBook(book2);
        System.out.println("updateBookById" + i1);
        System.out.println("==============");
        Book book3 = bookService.getBookById(1);
        System.out.println("getBookById" + book3);
        System.out.println("==============");
        int i2 = bookService.deleteBook(2);
        System.out.println("deleteBook" + i2);
        System.out.println("==============");
        List<Book> booksAll = bookService.getBooksAll();
        System.out.println("getBooksAll" + booksAll);
    }
}
