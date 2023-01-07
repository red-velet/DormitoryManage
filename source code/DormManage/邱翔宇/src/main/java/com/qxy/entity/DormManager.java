package com.qxy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 16:17
 * @Introduction:
 */
public class DormManager implements Serializable {
    private static final long serialVersionUID = -186388844370490252L;
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
}
