package org.caseor.boot.datasource.service.impl;

import org.caseor.boot.datasource.entity.User;
import org.caseor.boot.datasource.mapper.UserMapper;
import org.caseor.boot.datasource.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Fu Kai
 * @since 2021/11/20
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int updateById(User user) {
        return userMapper.updateById(user);
    }



}
