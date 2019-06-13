package com.xk.warehouse.model.entity;

import com.xk.framework.jpa.reposiotry.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * DemoPosition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_position", catalog = "xkdemo2")
public class DemoPosition extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// Fields
	private DemoArea area;
	private String positioncode;
	private String positionname;
	private Set<Posdetail> posdetails = new HashSet<Posdetail>(0);

	// Constructors

	/** default constructor */
	public DemoPosition() {
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "areaid")
	public DemoArea getArea() {
		return this.area;
	}

	public void setArea(DemoArea area) {
		this.area = area;
	}

	@Column(name = "positioncode", nullable = false, length = 50)
	public String getPositioncode() {
		return this.positioncode;
	}

	public void setPositioncode(String positioncode) {
		this.positioncode = positioncode;
	}

	@Column(name = "positionname", nullable = false, length = 50)
	public String getPositionname() {
		return this.positionname;
	}

	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "position")
	public Set<Posdetail> getPosdetails() {
		return this.posdetails;
	}
	public void setPosdetails(Set<Posdetail> posdetails) {
		this.posdetails = posdetails;
	}
}