package com.xk.warehouse.model.entity;

import com.xk.framework.jpa.reposiotry.base.BaseEntity;

import javax.persistence.*;

/**
 * DemoIndetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_indetail", catalog = "xkdemo2")
public class DemoIndetail extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// Fields
	private DemoInlist inlist;
	private DemoProduct product;
	private Double qty;
	private String positionid;

	// Constructors

	/** default constructor */
	public DemoIndetail() {
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "inlistid")
	public DemoInlist getInlist() {
		return this.inlist;
	}

	public void setInlist(DemoInlist inlist) {
		this.inlist = inlist;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productid")
	public DemoProduct getProduct() {
		return this.product;
	}

	public void setProduct(DemoProduct product) {
		this.product = product;
	}

	@Column(name = "qty", precision = 65, scale = 30)
	public Double getQty() {
		return this.qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	@Column(name = "positionid", length = 10)
	public String getPositionid() {
		return this.positionid;
	}

	public void setPositionid(String positionid) {
		this.positionid = positionid;
	}

}