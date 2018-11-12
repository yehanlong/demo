package com.yuanju.demo.utils;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Setter
@Getter
@ToString
public class PageDTO {
	
	private PageDTO() {}
	
	public static final <T> PageDTO getPagination(int total, List<T> rows){
		PageDTO pagination = new PageDTO();
		pagination.setRows(rows);
		pagination.setTotal(total);
		return pagination;
	};
	
	@SuppressWarnings("rawtypes")
	private List rows;//需要显示的数据

	private int total;//总的页数


}