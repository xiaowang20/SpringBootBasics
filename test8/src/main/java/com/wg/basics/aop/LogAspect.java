package com.wg.basics.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 制定切面（此切面是监控service所有方法）
 * 注：
 *  1.aop中的基本概念：
*                  1.连接点(JoinPoint)：所有可增强可方法
 *                 2.切入点：是一个定义，以那个方法切入
 *                 3.通知：前置通知、后置通知、异常通知、最终通知、环绕通知(可实现所有通知)
 *                 4.切面：切入点和通知组合成切面
 *                 5.目标对象：要增强的类就叫做增强对象
 *  2.本类是一个日志类，首先定义一个切入点（pointCut）为pc,处理要以那个类、方法、返回值、参数。
 *  第一个*：返回值、第二个*：类、第三个*：方法
 *  两个点：表示参数任意
 *  3.通知
 *
 */

@Component
@Aspect
public class LogAspect {
    @Pointcut(value = "execution(* com.wg.basics.aop.*.*(..))")
    public void pc(){

    }

    @Before(value = "pc()")
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法执行前执行.....");
    }

    @After(value = "pc()")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法执行后执行.....");
    }

    @AfterThrowing(value = "pc()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,Exception e){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "抛出异常通知" + e.getMessage());
    }

    @AfterReturning(value = "pc()",returning = "result")
    public void afterReturn(JoinPoint joinPoint,Object result){
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "返回通知，返回参数" + result);
    }

    @Around(value = "pc()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        return pjp.proceed();
    }
}
