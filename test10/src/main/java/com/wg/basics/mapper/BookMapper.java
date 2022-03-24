package com.wg.basics.mapper;

import com.wg.basics.entity.Book;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "c1")
public class BookMapper {
    //默认的缓存的key是参数，缓存的value是返回值
    @Cacheable
    public Book getBookById(Integer id){
        System.out.println("getBookById");
        System.out.println("============");
        Book book = new Book();
        book.setId(id);
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        return book;
    }
    //作用在更新方法上，直接调用方法查询，不经过缓存。经过方法查询之后，更新缓存（如果之前已经缓存过了，覆盖之前的缓存）
    @CachePut(key = "#book.id")
    public Book updateBookById(Book book){
        System.out.println("updateBookById");
        System.out.println("============");
        book.setName("神雕侠侣");
        return book;
    }

    //作用在删除方法上，有两个属性，1删除所有数据，2.true在方法前删除，false在方法后删除
    @CacheEvict(key = "#id")
    public void deleteBookById(Integer id){
        System.out.println("deleteBook");
        System.out.println("==========");
    }

}
