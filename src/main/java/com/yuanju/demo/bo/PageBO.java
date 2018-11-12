package com.yuanju.demo.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageBO  {
    private Integer page;			  //页数
    private Integer rowNum;			  //每页显示得条数
    private Integer startPage;		  //起始页
}
