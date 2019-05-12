package com.xk.demo.model.dto;

import com.xk.framework.jpa.reposiotry.base.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述：班级信息DTO
 *
 * @author LV
 * @date 2019-05-04
 */
@ApiModel(value = "班级信息DTO", description = "班级信息DTO")
public class DemoClassroomDto extends BaseDto {

    /**
     *
     */
    @ApiModelProperty(value = "")
    private String name;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String grade;
    /**
     * 学校ID
     */
    @ApiModelProperty(value = "学校ID")
    private String schoolid;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    public String getSchoolid() {
        return this.schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }


}