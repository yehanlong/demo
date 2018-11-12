package com.yuanju.demo.service.user.Impl;

import com.yuanju.demo.bo.PageBO;
import com.yuanju.demo.mapper.UserMapper;
import com.yuanju.demo.po.UserPO;
import com.yuanju.demo.service.common.RedisService;
import com.yuanju.demo.service.user.UserService;
import com.yuanju.demo.utils.PageDTO;
import com.yuanju.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

//    @Resource
//    private UserMapper userMapper;
    private final UserMapper userMapper;
    @Autowired
    RedisService redisService;

    @Autowired
    public  UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public UserVO findUserById(Integer UserId){
        UserVO userVO = userMapper.findUserById(UserId);
        return userVO;
    }

    @Override
    @Transactional
    public void userSignUp(UserPO userPO) {
        userMapper.userSignUp(userPO);
        redisService.add("2",2);
    }

//    @Override
//    public Integer userSignIn(UserPO userPO) {
//        return  userMapper.userSignIn(userPO);
//    }

    @Override
    public PageDTO findAllUser(PageBO pageBO) {
        int count = userMapper.AllUserCount();
        List<UserPO> userPOList = userMapper.findAllUser(pageBO);
        return PageDTO.getPagination(count,userPOList);
    }
}
