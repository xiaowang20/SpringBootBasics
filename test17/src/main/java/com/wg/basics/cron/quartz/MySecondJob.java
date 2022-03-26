package com.wg.basics.cron.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 可以是继承抽象的QuartzJobBean类
 */
@Component
public class MySecondJob extends QuartzJobBean {
    //可以设置参数
    private String name;
    public void setName(String name){
        this.name=name;
    }
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("第二个任务带参数：mySecondJob:"+name+":"+new Date());
    }
}
