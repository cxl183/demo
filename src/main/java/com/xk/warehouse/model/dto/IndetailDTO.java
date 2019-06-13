package com.xk.warehouse.model.dto;

import com.xk.framework.jpa.reposiotry.base.BaseDto;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "入库明细信息DTO", description = "入库明细信息DTO")
public class IndetailDTO extends BaseDto {
    private String productcode;
    private String productname;
    private Double  qty;
    private String depotname;
    private String areaname;
    private String positionname;
    private String productid;
    private String positionid;
    private String inlistid;
    public String getProductid() {
        return productid;
    }

    public String getPositionid() {
        return positionid;
    }

    public String getInlistid() {
        return inlistid;
    }

    public void setInlistid(String inlistid) {
        this.inlistid = inlistid;
    }

    public void setPositionid(String positionid) {
        this.positionid = positionid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
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
}

