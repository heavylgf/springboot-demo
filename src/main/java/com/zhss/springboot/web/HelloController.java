package com.zhss.springboot.web;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 它就是一个spring mvc的注解
 * @RestController就表明说，这个是一个spring mvc的controller
 * @RestController的意思是说，就仅仅提供RESTful接口，返回结果给浏览器，不会走传统读渲染模板视图页面
 * 我们这里是一个demo，所以说简化了一下，就将spring mvc的controller和入口类合并到一起了
 *
 */
@RestController
public class HelloController {

    // 如果说发送过来的请求是/hello/leo
    // 那么此时{name}这个东西就是leo，同时@PathVariable注解，会将{name}代表的值，注入方法入参
    // String name这个参数
    @RequestMapping("/hello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return "hello, " + name +", this is spring boot demo";
    }

}
