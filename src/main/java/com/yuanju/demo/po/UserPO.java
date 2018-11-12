package com.yuanju.demo.po;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserPO  {
    private Integer UserId;
    private String Name;
    private String Pwd;
    private Integer Age;
    private String Phone;
    private String Address;
}
