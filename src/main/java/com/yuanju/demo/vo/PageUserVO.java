package com.yuanju.demo.vo;

import com.yuanju.demo.po.UserPO;
import lombok.Data;

import java.util.List;

@Data
public class PageUserVO {
    private String Name;
    private String Pwd;
    private Integer Age;
    private String Phone;
    private String Address;
    //private List<UserPO> userPOList;

}
