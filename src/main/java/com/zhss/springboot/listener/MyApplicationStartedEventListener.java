package com.zhss.springboot.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 告诉编译器忽略指定的警告，不用在编译完成后出现警告信息。
 *
 * 如果使用了使用@Deprecated注释的方法，编译器将出现警告信息。使用这个注释将警告信息去掉。
 */
@SuppressWarnings("deprecation")
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    public void onApplicationEvent(ApplicationStartedEvent event) {
    	System.out.println("系统启动了。。。");  
    }
    
}