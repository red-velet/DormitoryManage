package com.qxy.entity;

import java.io.Serializable;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 16:19
 * @Introduction:
 */
public class UserAndBuildPlus implements Serializable {
    private static final long serialVersionUID = 8204809125033859638L;
    private Integer id;
    private String name;
    private String stu_code;
    private String passWord;
    private String sex;
    private String tel;
    private String buildings;

    @Override
    public String toString() {
        return "UserAndBuildPlus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stu_code='" + stu_code + '\'' +
                ", passWord='" + passWord + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", buildings='" + buildings + '\'' +
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

    public String getStu_code() {
        return stu_code;
    }

    public void setStu_code(String stu_code) {
        this.stu_code = stu_code;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    public String getBuildings() {
        return buildings;
    }

    public void setBuildings(String buildings) {
        this.buildings = buildings;
    }
}
