package com.zhss.springboot.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhss.springboot.domain.User;
import com.zhss.springboot.service.UserService;

/**
 * 用户管理模块的控制器组件
 *
 * 先初步介绍一下，什么是RESTful风格的接口，其实这个是一种风格和思想
 * 就是说他认为系统里的各种东西都是资源，暴露出去的接口，都是对资源的一种操作
 * 所以在请求URL里面，按照一种风格标志出来你要操作的是哪个资源
 * 然后通过HTTP method来定义你要对这个资源执行什么样的操作呢？
 *
 * @author
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Log log = LogFactory.getLog(UserController.class);

    /**
     * 用户管理模块的service组件
     */
    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @return 用户信息
     *
     * 这个@GetMapping注解表示的就是，这个接口仅仅接收GET类型的http请求
     *
     */
    @GetMapping("/")
    public List<User> listUsers() {
        log.info("查询所有用户信息");
        return userService.listUsers();
    }

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     *
     * {id}，就是通过占位符的方式，可以让我们提取请求URL中的参数
     *
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    /**
     * 新增用户
     * @param user 用户信息
     */
//    @PostMapping("/")
//    public void saveUser(@RequestBody User user) {
//        userService.saveUser(user);
//    }
// 这里就声明了要激活Add group对应的校验注解
// 那么就会校验user的userId不能为空
    @PostMapping("/")
    public String saveUser(
            @RequestBody @Validated({User.Save.class}) User user,
            BindingResult result) {
        // 如果校验失败，则将校验失败信息返回
        if(result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            FieldError error = (FieldError)errors.get(0);
            String message = error.getObjectName() + ","
                    + error.getField() + ","
                    + error.getDefaultMessage();
            return "{'status': 'error', 'message': '" + message + "'}";
        }
        userService.saveUser(user);
//        return "success";
        return "{'status': 'success', 'message': '新增用户ID为" + user.getId() + "'}";
    }

    /**
     * 更新用户
     * @param user 用户信息
     */
    @PutMapping("/{id}")
    public void updateUser(@RequestBody User user) {
//        log.info("更新用户.....");
        userService.updateUser(user);
    }

    /**
     * 删除用户
     * @param id 用户ID
     */
    @DeleteMapping("{id}")
    public void removeUser(@PathVariable("id") Long id) {
//        log.info("删除用户.....");
//
//        System.out.println("id: " + id);
        userService.removeUser(id);
    }

}

