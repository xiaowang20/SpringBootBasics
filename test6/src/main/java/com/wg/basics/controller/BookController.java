package com.wg.basics.controller;

import com.wg.basics.entity.Author;
import com.wg.basics.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * book和author中都有name,使用toString()得到的值为null;
 * 在全局配置里面使用@InitBinder配置,在需要的controller里面使用@ModelAttribute。
 * 即可分清各自的name
 */
@RestController
public class BookController {

    @GetMapping("/book")
    public String book(@ModelAttribute("a") Book book, @ModelAttribute("b") Author author){
        return book.toString() + "《===》" + author.toString();
    }
}
