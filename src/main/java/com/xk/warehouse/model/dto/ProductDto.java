package com.xk.warehouse.model.dto;

import com.xk.framework.jpa.reposiotry.base.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "产品信息DTO", description = "产品信息DTO")
public class ProductDto extends BaseDto {
	@ApiModelProperty(value = "产品id")
  private String productcode;
	@ApiModelProperty(value = "产品名称")
  private String productname;
	@ApiModelProperty(value = "规格")
  private String spec;//规格
	@ApiModelProperty(value = "计量单位")
  private String unit;//计量单位
	@ApiModelProperty(value = "单价")
  private Double unitprice;
	@ApiModelProperty(value = "类别")
  private String categoryname;//类别
	@ApiModelProperty(value = "状态")
   private String status;
  public String getStaus() {
	return status;
}
public void setStaus(String staus) {
	this.status = staus;
}
public String getProductcode() {
	return productcode;
}
public void setProductcode(String productcode) {
	this.productcode = productcode;
}
public String getProductname() {
	return productname;
}
public void setProductname(String productname) {
	this.productname = productname;
}
public String getSpec() {
	return spec;
}
public void setSpec(String spec) {
	this.spec = spec;
}
public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}
public Double getUnitprice() {
	return unitprice;
}
public void setUnitprice(Double unitprice) {
	this.unitprice = unitprice;
}
public String getCategoryname() {
	return categoryname;
}
public void setCategoryname(String categoryname) {
	this.categoryname = categoryname;
}
}
