package com.xk.warehouse.model.dto;

import com.xk.framework.jpa.reposiotry.base.BaseDto;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "部门信息DTO", description = "部门信息DTO")
public class DepotDTO extends BaseDto {
    private String depotname;

    public String getDepotname() {
        return depotname;
    }
    public void setDepotname(String depotname) {
        this.depotname = depotname;
    }
}
