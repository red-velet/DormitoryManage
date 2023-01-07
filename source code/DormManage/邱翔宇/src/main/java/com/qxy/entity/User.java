package com.qxy.entity;

import java.io.Serializable;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 10:13
 * @Introduction: 表tb_user对应的实体类
 */
public class User implements Serializable {
    private static final long serialVersionUID = 5127614594300389472L;
    private Integer id;
    private String name;
    private String passWord;
    private String stu_code;
    private String dorm_Code;
    private String sex;
    private String tel;
    private Integer dormBuildId;
    private Integer role_id;
    private Integer create_user_id;
    private Integer disabled;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                ", stu_code='" + stu_code + '\'' +
                ", dorm_Code='" + dorm_Code + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", dormBuildId=" + dormBuildId +
                ", role_id=" + role_id +
                ", create_user_id=" + create_user_id +
                ", disabled=" + disabled +
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

    public Integer getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(Integer dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(Integer create_user_id) {
        this.create_user_id = create_user_id;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }
}
