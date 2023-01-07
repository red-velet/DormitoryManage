package com.qxy.service;

import com.qxy.constant.Role;
import com.qxy.dao.SuperManagerDao;
import com.qxy.entity.*;
import com.qxy.exception.*;

import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 10:20
 * @Introduction:
 */
public class SuperManagerService {
    SuperManagerDao smd = new SuperManagerDao();
    CommonService cs = new CommonService();

    /**
     * 分页获取角色为宿舍管理员的所有数据信息和ta管理的宿舍信息集合
     *
     * @param dormManager 角色-宿舍管理员
     * @param currPage    起始页
     * @param pageSize    页数大小
     * @return 宿舍管理员的所有数据信息和ta管理的宿舍信息集合
     */
    public List<UserAndBuild> getAllDormManagerList(Integer dormManager, Integer currPage, Integer pageSize) {
        return smd.getAllDormManagerList(dormManager, (currPage - 1) * pageSize, pageSize);
    }

    /**
     * 分页获取角色为学生的所有数据信息和ta管理的宿舍信息集合
     *
     * @param student  角色-学生
     * @param currPage 起始页
     * @param pageSize 页数大小
     * @return 学生的所有数据信息和ta住的宿舍信息集合
     */
    public List<Student> getAllStudentList(Integer student, Integer currPage, Integer pageSize) {
        return smd.getAllStudentList(student, (currPage - 1) * pageSize, pageSize);
    }

    /**
     * 获取角色为宿舍管理员的所有数据信息和ta管理的宿舍信息的总条数
     *
     * @param dormManager 角色-宿舍管理员
     * @return 数据总条数
     */
    public long getAllDormManagerCount(Integer dormManager) {
        return smd.getAllDormManagerCount(dormManager);
    }

    /**
     * 获取角色为学生的所有数据信息和ta居住的宿舍信息的总条数
     *
     * @param student 角色-学生
     * @return 数据总条数
     */
    public long getAllStudentCount(Integer student) {
        return smd.getAllStudentCount(student);
    }

    /**
     * 该方法用于添加宿舍管理员
     *
     * @param username
     * @param password
     * @param rPassword
     * @param sex
     * @param tel
     * @param role
     * @param build
     * @param create_user_id
     * @param nickname
     */
    public void addDormManager(String username, String password, String rPassword, String sex, String tel, Integer role, String build, Integer create_user_id, String nickname) {
        //输入检查
        if (username == null || password == null || !password.equals(rPassword)) {
            throw new InputException();
        }
        //2.检查用户是否已注册
        if (cs.checkUserIsExist(username, role)) {
            throw new UserIsExistException();
        }
        //3.插入数据到tb_user
        smd.addDormManager(username, password, sex, tel, role, create_user_id, nickname);
        //4.获取用户id
        Integer id = smd.getDormManagerIdByUsername(username);
        //4.插入数据到tb_manager_dormbuild
        String[] builds = build.split(",");
        for (String buildId : builds) {
            smd.addDormBuildMapper(id, buildId);
        }
    }

    /**
     * 该方法用于添加学生
     *
     * @param nickname    姓名
     * @param username    用户名/学号
     * @param password    密码
     * @param rPassword   重复输入的密码
     * @param sex         性别
     * @param tel         联系方式
     * @param build       所住的宿舍楼栋
     * @param dormCode    寝室信息
     * @param role        角色
     * @param currLoginId 当前登录对象的id
     */
    public void addStudent(String nickname, String username, String password, String rPassword, String sex, String tel, Integer build, String dormCode, Integer role, Integer currLoginId) {
        //输入检查
        if (username == null || password == null || !password.equals(rPassword)) {
            throw new InputException();
        }
        //2.检查用户是否已注册
        if (cs.checkUserIsExist(username, role)) {
            throw new UserIsExistException();
        }
        //3.插入数据到tb_user
        smd.addStudent(nickname, username, password, sex, tel, build, dormCode, role, currLoginId);
    }

    public UserAndBuildPlus getDormById(Integer id) {
        return smd.getDormById(id);
    }

    /**
     * 通过用户的id获取用户的信息
     *
     * @param id id
     * @return 学生信息
     */
    public Student getStudentById(Integer id) {
        return smd.getStudentById(id);
    }

    public void updateDormManager(Integer id, String username, String password, String rPassword, String sex, String tel, Integer role, String build, String nickname) {
        //1.输入检查
        if (username == null || password == null || !password.equals(rPassword)) {
            throw new InputException();
        }
        //2.修改tb_user数据
        smd.updateDormManager(id, username, password, sex, tel, nickname, role);
        //3.修改tb_manager_dormbuild表
        //4.修改tb_manager_dormbuild表
        String[] builds = build.split(",");
        //先删除再添加
        smd.deleteDormBuildMapperByUserId(id);
        for (String buildId : builds) {
            smd.addDormBuildMapper(id, buildId);
        }
    }

    /**
     * 通过id修改学生信息
     *
     * @param id        学生id
     * @param nickname  姓名
     * @param username  用户名/学号
     * @param password  密码
     * @param rPassword 重复密码
     * @param dormCode  寝室号
     * @param buildId   宿舍楼
     * @param sex       性别
     * @param tel       联系方式
     * @param role      角色id
     */
    public void updateStudent(Integer id, String nickname, String username, String password, String rPassword, String dormCode, Integer buildId, String sex, String tel, Integer role) {
        //1.输入检查
        if (username == null || password == null || !password.equals(rPassword)) {
            throw new InputException();
        }
        //2.修改tb_user数据
        smd.updateStudent(id, nickname, username, password, dormCode, buildId, sex, tel, role);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     */
    public void deleteDormManagerById(Integer id) {
        //1.删除tb_manager_dormbuild表内映射
        smd.deleteDormBuildMapperByUserId(id);
        //2.删除tb_user表内数据
        smd.deleteUserById(id);
    }


    /**
     * 根据id删除用户
     *
     * @param id
     */
    public void deleteStudentById(Integer id) {
        //删除tb_user表内数据
        smd.deleteUserById(id);
    }

    public List<DormBuild> getAllBuildList(Integer currPage, Integer pageSize) {
        return smd.getAllBuildList((currPage - 1) * pageSize, pageSize);
    }

    public long countAllBuildList() {
        return smd.countAllBuildList();
    }

    /**
     * 该方法用于添加宿舍楼
     *
     * @param name   宿舍名称
     * @param remark 宿舍简介
     */
    public void addBuild(String name, String remark) {
        //1.检查输入
        if (name == null || remark == null) {
            throw new InputIsNullException();
        }
        //2.宿舍名不可重复,检查宿舍名
        if (cs.checkBuildIsExist(name)) {
            throw new BuildIsExistException();
        }
        smd.addBuild(name, remark);
    }

    public DormBuild getBuildById(Integer id) {
        return smd.getBuildById(id);
    }

    public void updateBuild(Integer id, String name, String remark) {
        if (name == null || remark == null) {
            throw new InputIsNullException();
        }
        if (cs.checkBuildIsExistPlus(name, id)) {
            throw new BuildIsExistException();
        }
        smd.updateBuild(id, name, remark);
    }

    public void deleteBuildById(Integer id) {
        smd.deleteBuildById(id);
    }

    /**
     * 该方法获取所有学生的缺勤记录
     *
     * @param student  角色-学生
     * @param currPage 分页起始
     * @param pageSize 页数据条数
     * @return 学生的缺勤记录集合
     */
    public List<RecordPlus> getAllRecordList(Integer student, Integer currPage, Integer pageSize) {
        return smd.getAllRecordList(student, (currPage - 1) * pageSize, pageSize);
    }

    public long countAllRecordList(Integer student) {
        return smd.countAllRecordList(student);
    }

    /**
     * 该方法添加学生缺勤记录
     *
     * @param stuCode
     * @param date
     * @param remark
     */
    public void addRecord(String stuCode, String date, String remark) {
        //1.输入检查
        if (stuCode == null || date == null || remark == null) {
            throw new InputIsNullException();
        }
        //2.学号检查:学生必须存在,且必须为学生
        if (!checkUserIsStudent(stuCode)) {
            throw new StudentIsNotExistException();
        }
        //3.添加缺勤记录
        //获取用户的id
        Integer student_id = getStudentIdByStuCodeAndRole(stuCode, Role.STUDENT);
        smd.addRecord(student_id, date, remark);
    }

    private Integer getStudentIdByStuCodeAndRole(String stuCode, Integer student) {
        return smd.getStudentIdByStuCodeAndRole(stuCode, student);
    }

    private boolean checkUserIsStudent(String stuCode) {
        //拿学号去查询表tb_user,条件为role_id为学生,学号为stuCode
        return smd.getStudentIdByStuCodeAndRole(stuCode, Role.STUDENT) != null;
    }

    /**
     * 通过rid获取正在修改的缺勤记录信息
     *
     * @param rid
     * @return
     */
    public RecordPlus getRecordByRid(Integer rid) {
        return smd.getRecordByRid(Role.STUDENT, rid);
    }

    public void updateRecord(Integer rid, String stuCode, String date, String remark) {
        //1.输入检查
        if (stuCode == null || date == null || remark == null) {
            throw new InputIsNullException();
        }
        //2.学号检查:学生必须存在,且必须为学生
        if (!checkUserIsStudent(stuCode)) {
            throw new StudentIsNotExistException();
        }
        //3.添加缺勤记录
        //获取用户的id
        Integer student_id = getStudentIdByStuCodeAndRole(stuCode, Role.STUDENT);
        smd.updateRecord(rid, student_id, date, remark);
    }

    /**
     * 通过缺勤编号删除缺勤记录
     *
     * @param id 缺勤编号
     */
    public void deleteRecordById(Integer id) {
        smd.deleteRecordById(id);
    }

    public List<RecordPlus> getMyAllRecordList(Integer id, Integer student, Integer currPage, Integer pageSize) {
        return smd.getMyAllRecordList(id, student, (currPage - 1) * pageSize, pageSize);
    }

    public long countMyAllRecordList(Integer id, Integer student) {
        return smd.countMyAllRecordList(id, student);
    }
}
