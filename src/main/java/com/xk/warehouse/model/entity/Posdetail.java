package com.xk.warehouse.model.entity;

import com.xk.framework.jpa.reposiotry.base.BaseEntity;

import javax.persistence.*;

/**
 * Posdetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_posdetail", catalog = "xkdemo2")
public class Posdetail extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// Fields
	private DemoProduct product;
	private DemoPosition position;
	private Double storenum;

	// Constructors

	/** default constructor */
	public Posdetail() {
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid")
	public DemoProduct getProduct() {
		return this.product;
	}

	public void setProduct(DemoProduct product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "positionid")
	public DemoPosition getPosition() {
		return this.position;
	}

	public void setPosition(DemoPosition position) {
		this.position = position;
	}

	@Column(name = "storenum", precision = 65, scale = 30)
	public Double getStorenum() {
		return this.storenum;
	}

	public void setStorenum(Double storenum) {
		this.storenum = storenum;
	}

}