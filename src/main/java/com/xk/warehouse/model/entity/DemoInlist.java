package com.xk.warehouse.model.entity;

import com.xk.framework.jpa.reposiotry.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * DemoInlist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_inlist", catalog = "xkdemo2")
public class DemoInlist extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// Fields
	private String Inlistcode;
	private String handler;
	private Date intime;
// Constructors

	/** default constructor */
	public DemoInlist() {
	}
	// Property accessors
	@Column(name = "inlistcode", nullable = false, length = 50)
	public String getInlistcode() {
		return this.Inlistcode;
	}

	public void setInlistcode(String inlistcode) {
		this.Inlistcode = inlistcode;
	}

	@Column(name = "handler", length = 30)
	public String getHandler() {
		return this.handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}
	@Column(name = "intime")
	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

}