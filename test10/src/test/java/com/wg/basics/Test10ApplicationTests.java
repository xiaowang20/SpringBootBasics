package com.wg.basics;

import com.wg.basics.entity.Book;
import com.wg.basics.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Test10ApplicationTests {
@Autowired
    BookMapper bookMapper;
    @Test
    void contextLoads() {
        bookMapper.getBookById(1);//第一次调用方法
        bookMapper.getBookById(1);//此次使用了缓存
        bookMapper.deleteBookById(1);//将key删除
        Book b3 = bookMapper.getBookById(1);
        System.out.println("b3" + b3);
        Book book = new Book();
        book.setId(1);
        book.setName("射雕英雄传");
        book.setAuthor("金庸");
        bookMapper.updateBookById(book);
        Book b4 = bookMapper.getBookById(1);//更新之后使用缓存
        System.out.println("b4" + b4);
    }

}
