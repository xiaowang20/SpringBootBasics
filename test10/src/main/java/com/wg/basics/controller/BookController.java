package com.wg.basics.controller;

import com.wg.basics.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 两个redis实例提供基本操作
 * StringRedisTemplate的key、value是String类型
 * RedisTemplate的key、value可以为对象。
 */
@RestController
@RequestMapping("/admin")
public class BookController {


    final StringRedisTemplate stringRedisTemplate;

    public BookController(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate=stringRedisTemplate;
    }
    //不建议使用@Autowired
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/book")
    public void testRedis(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("name","红楼梦");
        String name = ops.get("name");
        System.out.println("StringRedisTemplate==>" + name);
        System.out.println("=============================");
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Book book = new Book();
//        book.setId(1);
//        book.setName("三国演义");
//        book.setAuthor("罗贯中");
//        valueOperations.set("book",book);
//        Object book1 = valueOperations.get("book");
        book.setId(2);
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        valueOperations.set("book1",book);
        Book book1 = (Book)valueOperations.get("book1");
        System.out.println("redisTemplate==>" + book1);
    }
}
