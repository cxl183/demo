package com.xk.demo.model.dto;

import com.xk.framework.jpa.reposiotry.base.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述：学生信息DTO
 *
 * @author LV
 * @date 2019-05-04
 */
@ApiModel(value = "学生信息DTO", description = "学生信息DTO")
public class DemoStudentDto extends BaseDto {

    /**
     *学生姓名
     */
    @ApiModelProperty(value = "学生姓名")
    private String name;
    /**
     *家庭地址
     */
    @ApiModelProperty(value = "家庭地址")
    private String address;
    /**
     *年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;
    /**
     *班级ID
     */
    @ApiModelProperty(value = "班级ID")
    private String cid;

    @ApiModelProperty(value = "班级名称")
    private String grade;

    @ApiModelProperty(value = "年级名称")
    private String classroom;



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}