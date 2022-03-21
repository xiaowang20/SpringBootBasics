package com.wg.basics.controller;

import com.wg.basics.entity.Book;
import com.wg.basics.entity.BookTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("admin")
public class BookTestController {
    @GetMapping("bookTest")
    public BookTest book(){
        BookTest b1 =new BookTest();
        b1.setName("天龙八部");
        b1.setAuthor("金庸");
        b1.setPrice(49f);
        b1.setPublicationDate(new Date());

        return b1;
    }
}
