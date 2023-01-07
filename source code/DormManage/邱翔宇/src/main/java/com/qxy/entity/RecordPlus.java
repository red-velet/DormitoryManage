package com.qxy.entity;

import java.io.Serializable;

/**
 * @Author: SayHello
 * @Date: 2023/1/5 0:48
 * @Introduction: 学生的缺勤记录的实体类
 */
public class RecordPlus implements Serializable {
    private static final long serialVersionUID = 6764390616251746463L;
    private Integer uid;
    private Integer rid;
    private String date;
    private String stu_code;
    private String name;
    private String sex;
    private String buildName;
    private String dorm_Code;
    private String remark;

    @Override
    public String toString() {
        return "RolePlus{" +
                "uid=" + uid +
                ", rid=" + rid +
                ", date='" + date + '\'' +
                ", stu_code='" + stu_code + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", buildName='" + buildName + '\'' +
                ", dorm_Code='" + dorm_Code + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStu_code() {
        return stu_code;
    }

    public void setStu_code(String stu_code) {
        this.stu_code = stu_code;
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

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getDorm_Code() {
        return dorm_Code;
    }

    public void setDorm_Code(String dorm_Code) {
        this.dorm_Code = dorm_Code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
