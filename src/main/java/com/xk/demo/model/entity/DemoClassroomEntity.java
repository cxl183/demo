package com.xk.demo.model.entity;

import javax.persistence.*;
import java.util.Date;

import com.xk.framework.jpa.reposiotry.base.BaseEntity;

/**
 * 描述：班级信息模型
 *
 * @author LV
 * @date 2019-05-04
 */
@Entity
@Table(name = "demo_classroom")
public class DemoClassroomEntity extends BaseEntity {

    /**
     *班级名称
     */
    @Column(name = "name")
    private String name;
    /**
     *班级编号
     */
    @Column(name = "grade")
    private String grade;
    /**
     * 学校ID
     */
    @Column(name = "schoolid")
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