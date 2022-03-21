package com.wg.basics.Controller;

import com.wg.basics.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
public class BookController {
    @GetMapping("/book")
    public ModelAndView book(){
        List<Book> books = new ArrayList<>();
        Book b1 = new Book();
        b1.setId(1);
        b1.setName("雪山飞狐");
        b1.setAuthor("金庸");
        Book b2 = new Book();
        b2.setId(2);
        b2.setName("倚天除龙记");
        b2.setAuthor("金庸");
        books.add(b1);
        books.add(b2);

        ModelAndView mv = new ModelAndView();
        mv.addObject("books",books);
        mv.setViewName("books");
        return mv;
    }

}
