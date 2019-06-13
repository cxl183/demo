package com.xk.warehouse.model.dto;

import com.xk.framework.jpa.reposiotry.base.BaseDto;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "库位信息DTO", description = "库位信息DTO")
public class PositionDTO extends BaseDto {
    private String positionname;

    public String getPositionname() {
        return positionname;
    }

    public void setPositionname(String positionname) {
        this.positionname = positionname;
    }
}
