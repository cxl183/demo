package com.xk.warehouse.model.entity;

import com.xk.framework.jpa.reposiotry.base.BaseEntity;

import javax.persistence.*;

/**
 * Stock entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_stock", catalog = "xkdemo2")
public class Stock extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// Fields
	private DemoDepot depot;
	private DemoProduct product;
	private Double productnum;

	// Constructors

	/** default constructor */
	public Stock() {
	}

	// Property accessors
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "depotid")
	public DemoDepot getDepot() {
		return this.depot;
	}

	public void setDepot(DemoDepot depot) {
		this.depot = depot;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid")
	public DemoProduct getProduct() {
		return this.product;
	}

	public void setProduct(DemoProduct product) {
		this.product = product;
	}

	@Column(name = "productnum", precision = 65, scale = 30)
	public Double getProductnum() {
		return this.productnum;
	}

	public void setProductnum(Double productnum) {
		this.productnum = productnum;
	}

}