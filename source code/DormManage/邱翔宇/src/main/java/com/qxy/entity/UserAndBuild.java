package com.qxy.entity;

import java.io.Serializable;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 13:44
 * @Introduction: 宿舍管理员和ta管理的宿舍楼栋的实体类
 */
public class UserAndBuild implements Serializable {
    private static final long serialVersionUID = 8597491358059641715L;
    private Integer id;
    private String name;
    private String sex;
    private String tel;
    private String buildings;

    @Override
    public String toString() {
        return "UserAndBuild{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
