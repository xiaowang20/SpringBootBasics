package com.wg.basics.Mvc;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

/**
 * 监听
 * 响应时作用
 * 程序出错后，最后调用requestDestroyed().
 */
@WebListener
public class MyListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("requestInitialized");
    }
}
