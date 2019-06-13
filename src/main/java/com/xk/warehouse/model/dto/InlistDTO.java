package com.xk.warehouse.model.dto;

import com.xk.framework.jpa.reposiotry.base.BaseDto;
import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel(value = "入库单信息DTO", description = "入库单信息DTO")
public class InlistDTO extends BaseDto {
    private String inlistcode;
    private String handler;
    private String instate;
    private Date intime;
    public String getInlistcode() {
        return inlistcode;
    }
    public void setInlistcode(String inlistcode) {
        this.inlistcode = inlistcode;
    }
    public String getHandler() {
        return handler;
    }
    public void setHandler(String staffname) {
        this.handler = staffname;
    }
    public String getInstate() {
        return instate;
    }
    public void setInstate(String instate) {
        this.instate = instate;
    }
    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }
}
