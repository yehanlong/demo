package com.yuanju.demo.controller;

import com.yuanju.demo.bo.PageBO;
import com.yuanju.demo.bo.UserBO;
import com.yuanju.demo.po.UserPO;
import com.yuanju.demo.service.common.RedisService;
import com.yuanju.demo.service.user.UserService;
import com.yuanju.demo.utils.PageDTO;
import com.yuanju.demo.utils.ResultResp;
import com.yuanju.demo.utils.SysLog;
import com.yuanju.demo.utils.ValidationUtil;
import com.yuanju.demo.vo.UserVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class);
    //private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
//    @Autowired
//    private UserService userService;

    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @Autowired
    private RedisService redisService;


    @ApiOperation(value = "获取用户信息", notes = "通过id获取用户")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Userid", paramType = "path", value = "用户ID")
    })
    @PostMapping(value = "/findUserById")
    public ResultResp findUserById(@RequestBody UserBO userBO) {
        UserVO userVO;
        try {
            userVO = userService.findUserById(userBO.getUserId());
            LOG.info("查询用户成功"+userBO.getUserId());
            SysLog.info("查询用户成功"+userBO.toString());
            return ResultResp.getResult(200, true, "查询用户成功", userVO);
        } catch (Exception e) {
            LOG.error("查询用户失败",e);
            return ResultResp.getResult(500, false, "查询用户失败", null);
        }
    }

    @PostMapping(value = "/signUp")
    public ResultResp userSignUp(@RequestBody UserBO userBO){
        try{
            ValidationUtil.checkNullAndAssignString(userBO.getName());
            //boolean isphone = ValidationUtil.isMobileNO(userBO.getPhone());
        }catch (Exception e){
            return ResultResp.getResult(500,false,"参数校验失败",null);
        }
        UserPO userPO = new UserPO();
        try{
            BeanUtils.copyProperties(userBO,userPO);
            userService.userSignUp(userPO);
            redisService.set("1","1");
            redisService.get("1");
            return ResultResp.getResult(200,true,"注册成功",userPO);
        }catch (Exception e){

            return ResultResp.getResult(500,false,"注册失败",null);
        }
    }


    @PostMapping(value = "/AllUser")
    public ResultResp findAllUser(@RequestBody PageBO pageBO){
        try{
            int page = ValidationUtil.checkMinAndAssignInt(pageBO.getPage(),1);
            int rowNum=ValidationUtil.checkMinAndAssignInt(pageBO.getRowNum(),1);
            pageBO.setStartPage((page-1)*rowNum);
        }catch (Exception e){
            return ResultResp.getResult(500,true,"参数校验失败",null);
        }
        try{
            PageDTO pageDTO =userService.findAllUser(pageBO);
            return ResultResp.getResult(200,true,"查询成功",pageDTO);
        }catch (Exception e){
            return ResultResp.getResult(500,false,"查询失败",null);
        }

    }
}
