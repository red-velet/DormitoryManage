package com.qxy.service;

import com.qxy.constant.Role;
import com.qxy.dao.CommonDao;
import com.qxy.entity.*;
import com.qxy.exception.InputException;
import com.qxy.exception.InputIsNullException;
import com.qxy.exception.UsernameOrPasswordIncorrectException;

import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 10:30
 * @Introduction:
 */
public class CommonService {
    CommonDao cd = new CommonDao();

    /**
     * 该方法获取所有宿舍楼信息并且返回
     *
     * @return 宿舍楼信息集合
     */
    public List<DormBuild> getBuildingList() {
        return cd.getBuildingList();
    }

    /**
     * 该方法用于用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回对应的对象, 否则抛出异常
     */
    public User login(String username, String password) {
        if (username == null || password == null) {
            throw new InputIsNullException();
        } else {
            User login = cd.login(username, password);
            if (login == null) {
                throw new UsernameOrPasswordIncorrectException();
            }
            return login;
        }
    }

    /**
     * @param username
     * @param role
     * @return
     */
    public boolean checkUserIsExist(String username, Integer role) {
        return cd.checkUserIsExist(username, role);
    }

    /**
     * 查询-昵称模糊查询
     *
     * @param condition 昵称
     * @param currPage
     * @param pageSize
     * @return 查询到的数据对应的实体类集合
     */
    public List<UserAndBuild> getDormManagerByNickname(String condition, Integer currPage, Integer pageSize) {
        return cd.getDormManagerByNickname(condition, (currPage - 1) * pageSize, pageSize);
    }

    /**
     * 通过名字进行查找用户信息
     *
     * @param content  下面
     * @param currPage 分页开始
     * @param pageSize 页数据大小
     * @return 查找到的数据集合
     */
    public List<Student> getStudentByNickname(String content, Integer currPage, Integer pageSize) {
        return cd.getStudentByNickname(content, (currPage - 1) * pageSize, pageSize);
    }


    public long countDormManagerByNickname(String condition) {
        return cd.countDormManagerByNickname(condition);
    }

    /**
     * 查询-性别模糊查询
     *
     * @param condition
     * @param currPage
     * @param pageSize
     * @return
     */
    public List<UserAndBuild> getDormManagerBySex(String condition, Integer currPage, Integer pageSize) {
        return cd.getDormManagerBySex(condition, (currPage - 1) * pageSize, pageSize);
    }

    public long countDormManagerBySex(String condition) {
        return cd.countDormManagerBySex(condition);
    }


    public long countStudentByNickname(String content) {
        return cd.countStudentByNickname(content);
    }

    public List<Student> getStudentBySex(String content, Integer currPage, Integer pageSize) {
        return cd.getStudentBySex(content, (currPage - 1) * pageSize, pageSize);
    }

    public long countStudentBySex(String content) {
        return cd.countStudentBySex(content);
    }

    public boolean checkBuildIsExistPlus(String name, Integer id) {
        Integer newId = cd.checkBuildIsExist(name);
        if (newId == null || id.equals(newId)) {
            return false;
        }
        return true;
    }

    public boolean checkBuildIsExist(String name) {
        return cd.checkBuildIsExist(name) != null;
    }


    public List<DormBuild> getBuildByNickname(String content, Integer currPage, Integer pageSize) {
        return cd.getBuildByNickname(content, (currPage - 1) * pageSize, pageSize);
    }

    public long countBuildByNickname(String content) {
        return cd.countBuildByNickname(content);
    }

    public List<RecordPlus> getRecordByDate(String content, Integer currPage, Integer pageSize) {
        String[] startAndEnd = content.split(" to ");
        String startTime = startAndEnd[0];
        String endTime = startAndEnd[1];
        return cd.getRecordByDate(startTime, endTime, (currPage - 1) * pageSize, pageSize, Role.STUDENT);
    }

    public long countRecordByDate(String content) {
        String[] startAndEnd = content.split(" to ");
        String startTime = startAndEnd[0];
        String endTime = startAndEnd[1];
        return cd.countRecordByDate(startTime, endTime, Role.STUDENT);
    }

    public void updateInfo(Integer id, String name, String stuCode, String password, String rPassword, String sex, String tel) {
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("stuCode = " + stuCode);
        System.out.println("password = " + password);
        System.out.println("rPassword = " + rPassword);
        System.out.println("sex = " + sex);
        System.out.println("tel = " + tel);
        if (name == null || stuCode == null || password == null || !password.equals(rPassword)) {
            throw new InputException();
        }
        cd.updateInfo(id, name, stuCode, password, sex, tel);
    }

    public List<RecordPlus> getMyRecordByDate(Integer id, String content, Integer currPage, Integer pageSize) {
        String[] startAndEnd = content.split(" to ");
        String startTime = startAndEnd[0];
        String endTime = startAndEnd[1];
        return cd.getMyRecordByDate(startTime, endTime, (currPage - 1) * pageSize, pageSize, Role.STUDENT, id);
    }

    public long countMyRecordByDate(Integer id, String content) {
        String[] startAndEnd = content.split(" to ");
        String startTime = startAndEnd[0];
        String endTime = startAndEnd[1];
        return cd.countMyRecordByDate(id, Role.STUDENT, startTime, endTime);
    }
}
