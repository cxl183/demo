package com.xk.demo.model.entity;

import javax.persistence.*;
import java.util.Date;

import com.xk.framework.jpa.reposiotry.base.BaseEntity;

/**
 * 描述：学生信息模型
 *
 * @author LV
 * @date 2019-05-04
 */
@Entity
@Table(name = "demo_student")
public class DemoStudentEntity extends BaseEntity {

    /**
     *学生姓名
     */
    @Column(name = "name")
    private String name;
    /**
     *家庭地址
     */
    @Column(name = "address")
    private String address;
    /**
     *学生年龄
     */
    @Column(name = "age")
    private Integer age;
    /**
     *班级ID
     */
    @Column(name = "cid")
    private String cid;

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


}