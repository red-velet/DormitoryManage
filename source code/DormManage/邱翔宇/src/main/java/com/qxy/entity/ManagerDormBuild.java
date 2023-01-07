package com.qxy.entity;

import java.io.Serializable;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 10:18
 * @Introduction: 表tb_manage_dormbuild对应的实体类
 */
public class ManagerDormBuild implements Serializable {
    private static final long serialVersionUID = -89863990588629950L;
    private Integer id;
    private Integer user_id;
    private Integer dormBuild_id;

    @Override
    public String toString() {
        return "ManagerDormBuild{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", dormBuild_id=" + dormBuild_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getDormBuild_id() {
        return dormBuild_id;
    }

    public void setDormBuild_id(Integer dormBuild_id) {
        this.dormBuild_id = dormBuild_id;
    }
}
