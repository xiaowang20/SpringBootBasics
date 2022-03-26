package com.wg.basics.cron.SpringBoot;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * springBoot自带的定时任务
 * 使用注解@Scheduled
 */
//@Component
public class MySchedule {

    @Scheduled(fixedDelay = 1000)
    public void fixedDelay(){
        System.out.println("当前任务结束后1s,执行下一任务"+new Date());
    }
    @Scheduled(fixedRate = 2000)
    public void fixedRate(){
        System.out.println("当前任务开始执行2s后，执行下一任务"+new Date());
    }
    @Scheduled(initialDelay = 1000,fixedRate = 2000)
    public void initialDelay(){
        System.out.println("首次执行的延时时间"+new Date());
    }
    @Scheduled(cron = "0 * * * * ?")
    public void cron(){
        System.out.println("表示每分钟执行一次"+new Date());
    }
}
