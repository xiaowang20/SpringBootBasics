package com.wg.basics.cron.quartz;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *  普通的javaBean
 */
@Component
public class MyFirstJob {
public void sayHello(){
    System.out.println("第一个普通任务：seaHello:"+ new Date());
}
}
