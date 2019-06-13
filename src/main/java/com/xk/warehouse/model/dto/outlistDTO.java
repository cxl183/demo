package com.xk.warehouse.model.dto;

import com.xk.framework.jpa.reposiotry.base.BaseDto;

import java.util.Date;

public class outlistDTO extends BaseDto {
    private String outlistcode;
    private String handler;
    private String outstate;
    private Date outtime;
    public String getOutlistcode() {
        return outlistcode;
    }

    public void setOutlistcode(String outlistcode) {
        this.outlistcode = outlistcode;
    }

    public String getHandler() {
        return handler;
    }
    public void setHandler(String staffname) {
        this.handler = staffname;
    }

    public String getOutstate() {
        return outstate;
    }

    public void setOutstate(String outstate) {
        this.outstate = outstate;
    }

    public Date getOuttime() {
        return outtime;
    }

    public void setOuttime(Date outtime) {
        this.outtime = outtime;
    }
}
