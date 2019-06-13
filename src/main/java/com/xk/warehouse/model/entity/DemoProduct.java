package com.xk.warehouse.model.entity;

import com.xk.framework.jpa.reposiotry.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * DemoProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_product", catalog = "xkdemo2")
public class DemoProduct extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// Fields
	/**
	 * 种类名称
	 */
	@ApiModelProperty(value = "种类名称")
	private DemoCategory demoCategory;
	/**
	 产品编号
	 */
	@ApiModelProperty(value = "产品编号")
	private String productcode;
	/**
	 产品名称
	 */
	@ApiModelProperty(value = "产品名称")
	private String productname;
	/**
	 规格
	 */
	@ApiModelProperty(value = "规格")
	private String spec;
	/**
	 单位
	 */
	@ApiModelProperty(value = "单位")
	private String unit;
	/**
	 单价
	 */
	@ApiModelProperty(value = "单价")
	private Double unitprice;

	// Constructors

	/** default constructor */
	public DemoProduct() {
	}

	// Property accessors

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryid", nullable = false)
	public DemoCategory getDemoCategory() {
		return this.demoCategory;
	}

	public void setDemoCategory(DemoCategory demoCategory) {
		this.demoCategory = demoCategory;
	}

	@Column(name = "productcode")
	public String getProductcode() {
		return this.productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	@Column(name = "productname")
	public String getProductname() {
		return this.productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	@Column(name = "spec")
	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	@Column(name = "unit")
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "unitprice", precision = 10)
	public Double getUnitprice() {
		return this.unitprice;
	}

	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}
}