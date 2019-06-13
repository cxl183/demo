package com.xk.warehouse.model.entity;

import com.xk.framework.jpa.reposiotry.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * DemoArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_area", catalog = "xkdemo2")
public class DemoArea extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private DemoDepot depot;
	private String areacode;
	private String areaname;
	private Set<DemoPosition> positions = new HashSet<DemoPosition>(0);

	// Constructors

	/** default constructor */
	public DemoArea() {
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "depotid")
	public DemoDepot getDepot() {
		return this.depot;
	}

	public void setDepot(DemoDepot depot) {
		this.depot = depot;
	}

	@Column(name = "areacode", nullable = false, length = 50)
	public String getAreacode() {
		return this.areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	@Column(name = "areaname", length = 50)
	public String getAreaname() {
		return this.areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "area")
	public Set<DemoPosition> getPositions() {
		return this.positions;
	}

	public void setPositions(Set<DemoPosition> positions) {
		this.positions = positions;
	}

}