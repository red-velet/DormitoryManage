package com.qxy.entity;

import java.io.Serializable;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 21:16
 * @Introduction:
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 8611776332412595259L;
    private Integer id;
    private String name;
    private String passWord;
    private String stu_code;
    private String dorm_Code;
    private String sex;
    private String tel;
    private Integer disabled;
    private String buildName;
    private String remark;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                ", stu_code='" + stu_code + '\'' +
                ", dorm_Code='" + dorm_Code + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", disabled=" + disabled +
                ", buildName='" + buildName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getStu_code() {
        return stu_code;
    }

    public void setStu_code(String stu_code) {
        this.stu_code = stu_code;
    }

    public String getDorm_Code() {
        return dorm_Code;
    }

    public void setDorm_Code(String dorm_Code) {
        this.dorm_Code = dorm_Code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
