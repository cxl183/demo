package com.xk.warehouse.model.entity;

import com.xk.framework.jpa.reposiotry.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Staff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_Staff", catalog = "xkdemo2")
public class Staff extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// Fields
	private String staffcode;
	private String staffname;
	private String account;
	private String password;
	private Set<DemoDepot> depots = new HashSet<DemoDepot>(0);

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** minimal constructor */
	public Staff( String staffcode) {
		this.staffcode = staffcode;
	}



	// Property accessors
	@Column(name = "staffcode", nullable = false, length = 20)
	public String getStaffcode() {
		return this.staffcode;
	}

	public void setStaffcode(String staffcode) {
		this.staffcode = staffcode;
	}

	@Column(name = "staffname", length = 50)
	public String getStaffname() {
		return this.staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	@Column(name = "account", length = 30)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password", length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staff")
	public Set<DemoDepot> getDepots() {
		return this.depots;
	}

	public void setDepots(Set<DemoDepot> depots) {
		this.depots = depots;
	}

}