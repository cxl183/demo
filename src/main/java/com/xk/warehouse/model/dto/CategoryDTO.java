package com.xk.warehouse.model.dto;

import com.xk.framework.jpa.reposiotry.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "分类信息DTO", description = "分类信息DTO")
public class CategoryDTO extends BaseDto {
	@ApiModelProperty(value = "分类主键")
	private String categoryid;
	@ApiModelProperty(value = "分类id")
	private String categorycode;
	@ApiModelProperty(value = "分类名称")
	private String categoryname;
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategorycode() {
		return categorycode;
	}
	public void setCategorycode(String categorycode) {
		this.categorycode = categorycode;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
}
