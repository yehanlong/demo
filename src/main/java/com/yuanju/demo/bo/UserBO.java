package com.yuanju.demo.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserBO  {
    private Integer UserId;
    private String Name;
    private Integer Age;
    private String Phone;
    private String Address;
    private String Pwd;
}
