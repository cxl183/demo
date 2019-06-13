package com.xk.warehouse.model.entity;

import com.xk.framework.jpa.reposiotry.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * DemoCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_category", catalog = "xkdemo2")
public class DemoCategory extends BaseEntity {
		private static final long serialVersionUID = 1L;
	/**
	 种类编号
	 */
	@ApiModelProperty(value = "种类编号")
	private String categorycode;
	/**
	 种类名称
	 */
	@ApiModelProperty(value = "种类名称")
	private String categoryname;
	private Set<DemoProduct> demoProducts = new HashSet<DemoProduct>(0);
	// Property accessors
	@Column(name = "code", length = 50)
	public String getCategorycode() {
		return this.categorycode;
	}

	public void setCategorycode(String categorycode) {
		this.categorycode = categorycode;
	}

	@Column(name = "name", length = 50)
	public String getCategoryname() {
		return this.categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "demoCategory")
	public Set<DemoProduct> getDemoProducts() {
		return this.demoProducts;
	}

	public void setDemoProducts(Set<DemoProduct> demoProducts) {
		this.demoProducts = demoProducts;
	}

}