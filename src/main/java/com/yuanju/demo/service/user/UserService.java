package com.yuanju.demo.service.user;

import com.yuanju.demo.bo.PageBO;
import com.yuanju.demo.po.UserPO;
import com.yuanju.demo.utils.PageDTO;
import com.yuanju.demo.vo.UserVO;

public interface UserService {
    UserVO findUserById(Integer UserId);
    void userSignUp (UserPO userPO);
    //Integer userSignIn (UserPO userPO);
    PageDTO findAllUser(PageBO pageBO);
}
