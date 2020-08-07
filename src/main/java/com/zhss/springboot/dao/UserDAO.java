package com.zhss.springboot.dao;

import java.util.List;

import com.zhss.springboot.domain.User;

/**
 * 用户管理模块的DAO组件接口
 * @author
 *
 */
public interface UserDAO {

    /**
     * 查询所有用户
     * @return 用户信息
     */
    List<User> listUsers();
    // List<User> listUsers();

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserById(Long id);
    // User getUserById(Long id);

    /**
     * 新增用户
     * @param user 用户信息
     */
    Long saveUser(User user);
    // void saveUser(User user)

    /**
     * 更新用户
     * @param user 用户信息
     */
    boolean updateUser(User user);
    // void updateUser(User user);

    /**
     * 删除用户
     * @param id 用户ID
     */
    boolean removeUser(Long id);
    // void removeUser(Long id);

}
