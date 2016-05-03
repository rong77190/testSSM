package com.xiechur.service.impl;

import com.xiechur.dao.UserDao;
import com.xiechur.model.User;
import com.xiechur.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * UserService实现类
 * @author Administrator
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    
    @Resource
    private UserDao userDao;
    
    @Override
    public User login(User user) {
        return userDao.login(user);
    }
    
    @Override
     public List<User> find(Map<String, Object> map) {
        return userDao.find(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return userDao.getTotal(map);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public int add(User user) {
        return userDao.add(user);
    }

    @Override
    public int delete(Integer id) {
        return userDao.delete(id);
    }
}