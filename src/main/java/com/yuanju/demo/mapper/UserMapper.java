package com.yuanju.demo.mapper;

import com.yuanju.demo.bo.PageBO;
import com.yuanju.demo.po.UserPO;
import com.yuanju.demo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
   UserVO findUserById(Integer UserId);
   void userSignUp (UserPO userPO);
   Integer userSignIn (UserPO userPO);
   List<UserPO> findAllUser(PageBO pageBO);
   Integer AllUserCount();
}
