package com.qxy.entity;

import java.io.Serializable;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 10:16
 * @Introduction: 表tb_record对应的实体类
 */
public class Record implements Serializable {
    private static final long serialVersionUID = -6616326827518859969L;
    private Integer id;
    private Integer student_id;
    private String date;
    private String remark;
    private Integer disabled;

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", date='" + date + '\'' +
                ", remark='" + remark + '\'' +
                ", disabled=" + disabled +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }
}
