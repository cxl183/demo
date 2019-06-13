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
@Table(name = "demo_outlist", catalog = "xkdemo2")
public class outlist extends BaseEntity {
    private static final long serialVersionUID = 1L;
    // Fields
    private String outlistcode;
    private String handler;
    private Date outtime;
    // Constructors

    /** default constructor */
    public outlist() {
    }
    // Property accessors
    @Column(name = "outlistcode", nullable = false, length = 50)
    public String getOutlistcode() {
        return this.outlistcode;
    }

    public void setOutlistcode(String outlistcode) {
        this.outlistcode = outlistcode;
    }

    @Column(name = "handler", length = 30)
    public String getHandler() {
        return this.handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    @Column(name = "outtime")
    public Date getOuttime() {
        return outtime;
    }

    public void setOuttime(Date outtime) {
        this.outtime = outtime;
    }
}