package com.xk.warehouse.model.entity;

import com.xk.framework.jpa.reposiotry.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * DemoDepot entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_depot", catalog = "xkdemo2")
public class DemoDepot extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// Fields
	private Staff staff;
	private String depotcode;
	private String depotname;
	private Set<Stock> stocks = new HashSet<Stock>(0);
	private Set<DemoArea> areas = new HashSet<DemoArea>(0);

	// Constructors

	/** default constructor */
	public DemoDepot() {
	}
	// Property accessors
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staffid", nullable = false)
	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Column(name = "depotcode", nullable = false, length = 50)
	public String getDepotcode() {
		return this.depotcode;
	}

	public void setDepotcode(String depotcode) {
		this.depotcode = depotcode;
	}

	@Column(name = "depotname", length = 50)
	public String getDepotname() {
		return this.depotname;
	}

	public void setDepotname(String depotname) {
		this.depotname = depotname;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "depot")
	public Set<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "depot")
	public Set<DemoArea> getAreas() {
		return this.areas;
	}

	public void setAreas(Set<DemoArea> areas) {
		this.areas = areas;
	}

}