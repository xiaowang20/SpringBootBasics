package com.wg.basics.cron.config;

import com.wg.basics.cron.quartz.MySecondJob;
import org.quartz.JobDataMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

/**
 * quartz定时任务配置类
 * 模板：主要提供三个bean：JobDetail、Trigger(主要使用：SimpleTrigger、cronTrigger)、ScheduledFactory
 * 注：
 *  1.使用JobDetail将需要的bean注入配置类中(主要有两种方式：MethodInvokingJobDetailFactoryBean)
 *  2.在Trigger中设置延迟时间的次数
 *  3.使用ScheduledFactory设置Trigger
 */
@Configuration
public class MyQuartzConfig {

    /**
     * 注入第一个普通任务
     * 不能在JobDetail创建时传参
     * @return
     */
    @Bean
    MethodInvokingJobDetailFactoryBean jobDetail1(){
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("myFirstJob");
        bean.setTargetMethod("sayHello");
        return bean;
    }

    /**
     * 注入第二个任务（带参数）
     * @return
     */
    @Bean
    JobDetailFactoryBean jobDetail2(){
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(MySecondJob.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name","wg");//设置参数
        bean.setJobDataMap(jobDataMap);
        bean.setDurability(true);
        return bean;
    }

    /**
     * 注入SimpleTriggerFactoryBean
     * @return
     */
    @Bean
    SimpleTriggerFactoryBean simpleTrigger(){
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setJobDetail(jobDetail1().getObject());//拿到任务
        bean.setRepeatCount(3);
        bean.setStartDelay(1000);
        bean.setRepeatInterval(2000);//指定此触发器的执行时间间隔。2s
        return bean;
    }

    /**
     * 注入CronTriggerFactoryBean
     * @return
     */
    @Bean
    CronTriggerFactoryBean cronTrigger(){
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetail2().getObject());//拿到任务
        bean.setCronExpression("* * * * * ?");//以cron方式注入
        return bean;
    }

    /**
     * 注入SchedulerFactoryBean
     * 设置Trigger
     * @return
     */
    @Bean
    SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(simpleTrigger().getObject(),cronTrigger().getObject());
        return bean;
    }
}
