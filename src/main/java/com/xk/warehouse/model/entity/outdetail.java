package com.xk.warehouse.model.entity;

import com.xk.framework.jpa.reposiotry.base.BaseEntity;

import javax.persistence.*;

/**
 * DemoIndetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "demo_outdetail", catalog = "xkdemo2")
public class outdetail extends BaseEntity {
    private static final long serialVersionUID = 1L;
    // Fields
    private outlist outlist;
    private DemoProduct product;
    private Double qty;
    private String positionid;

    // Constructors

    /** default constructor */
    public outdetail() {
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outlistid")
    public outlist getOutlist() {
        return this.outlist;
    }

    public void setOutlist(outlist outlist) {
        this.outlist = outlist;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    public DemoProduct getProduct() {
        return this.product;
    }

    public void setProduct(DemoProduct product) {
        this.product = product;
    }

    @Column(name = "QTY", precision = 65, scale = 30)
    public Double getQty() {
        return this.qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    @Column(name = "POSITIONID", length = 10)
    public String getPositionid() {
        return this.positionid;
    }

    public void setPositionid(String positionid) {
        this.positionid = positionid;
    }

}
