package com.xk.warehouse.model.dto;

import com.xk.framework.jpa.reposiotry.base.BaseDto;

public class outdetailDTO extends BaseDto {
    private String productcode;
    private String productname;
    private Double  qty;
    private String depotname;
    private String areaname;
    private String positionname;
    private String productid;
    private String positionid;
    private String outlistid;
    private String categoryname;
    private Double leastqty;

    public Double getLeastqty() {
        return leastqty;
    }

    public void setLeastqty(Double leastqty) {
        this.leastqty = leastqty;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public String getDepotname() {
        return depotname;
    }

    public void setDepotname(String depotname) {
        this.depotname = depotname;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getPositionname() {
        return positionname;
    }

    public void setPositionname(String positionname) {
        this.positionname = positionname;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getPositionid() {
        return positionid;
    }

    public void setPositionid(String positionid) {
        this.positionid = positionid;
    }

    public String getOutlistid() {
        return outlistid;
    }

    public void setOutlistid(String outlistid) {
        this.outlistid = outlistid;
    }
}
