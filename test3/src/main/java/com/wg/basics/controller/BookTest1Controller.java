package com.wg.basics.controller;

import com.wg.basics.entity.BookTest;
import com.wg.basics.entity.BookTest1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("admin")
public class BookTest1Controller {
    @GetMapping("bookTest1")
    public BookTest1 book(){
        BookTest1 b1 =new BookTest1();
        b1.setName("天龙八部");
        b1.setAuthor("金庸");
        b1.setPrice(49f);
        b1.setPublicationDate(new Date());

        return b1;
    }
}
