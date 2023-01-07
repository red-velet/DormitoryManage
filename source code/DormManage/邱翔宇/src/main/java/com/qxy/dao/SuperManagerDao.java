package com.qxy.dao;

import com.qxy.entity.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 13:49
 * @Introduction:
 */
public class SuperManagerDao extends BasicDao {
    QueryRunner qr = new QueryRunner();

    /**
     * 获取角色为宿舍管理员的信息和ta所管理的宿舍信息
     *
     * @param dormManager 宿舍管理员
     * @param start       开始
     * @param pageSize    大小
     * @return 宿舍管理员的信息和ta所管理的宿舍信息集合
     */
    public List<UserAndBuild> getAllDormManagerList(Integer dormManager, Integer start, Integer pageSize) {
        Connection conn = getConn();
        String sql = "select u.id,u.`name`,u.sex,u.tel,GROUP_CONCAT(d.`name`) buildings\n" +
                "from tb_user u \n" +
                "LEFT JOIN tb_manage_dormbuild md on u.id = md.user_id\n" +
                "LEFT JOIN tb_dormbuild d on md.dormBuild_id = d.id\n" +
                "WHERE u.role_id = ?\n" +
                "GROUP BY u.id,u.`name`,u.sex,u.tel\n" +
                "ORDER BY u.id\n" +
                "LIMIT ?,?;";
        List<UserAndBuild> list = null;
        try {
            list = qr.query(conn, sql, new BeanListHandler<>(UserAndBuild.class),
                    dormManager,
                    start,
                    pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return list;
    }

    /**
     * 获取角色为学生的信息和ta所居住的宿舍信息
     *
     * @param student  学生
     * @param start    开始
     * @param pageSize 大小
     * @return 学生的信息和ta所居住的宿舍信息集合
     */
    public List<Student> getAllStudentList(Integer student, Integer start, Integer pageSize) {
        Connection conn = getConn();
        String sql = "SELECT u.id,u.`name`,u.`passWord`,u.stu_code,u.dorm_Code,u.sex,u.tel,u.disabled,d.`name` buildName,d.remark \n" +
                "FROM tb_user u LEFT JOIN tb_dormbuild d ON u.dormBuildId = d.id\n" +
                "WHERE u.role_id = ?\n" +
                "ORDER BY u.id\n" +
                "LIMIT ?,?;";
        List<Student> list = null;
        try {
            list = qr.query(conn, sql, new BeanListHandler<>(Student.class),
                    student,
                    start,
                    pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return list;
    }

    /**
     * 分页获取表tb_dormbuild的所有信息
     *
     * @param start    分页开始
     * @param pageSize 页内数据大小
     * @return 查询到的集合
     */
    public List<DormBuild> getAllBuildList(Integer start, Integer pageSize) {
        Connection conn = getConn();
        String sql = "SELECT id,`name`,remark,disabled FROM tb_dormbuild ORDER BY id LIMIT ?,?;";
        List<DormBuild> list = null;
        try {
            list = qr.query(conn, sql, new BeanListHandler<>(DormBuild.class),
                    start,
                    pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return list;
    }

    /**
     * 宿舍管理员的信息和ta所管理的宿舍信息的总条数
     *
     * @param dormManager 宿舍管理员
     * @return 数据总条数
     */
    public long getAllDormManagerCount(Integer dormManager) {
        Connection conn = getConn();
        String sql = "select COUNT(DISTINCT(u.id))\n" +
                "from tb_user u \n" +
                "LEFT JOIN tb_manage_dormbuild md on u.id = md.user_id\n" +
                "LEFT JOIN tb_dormbuild d on md.dormBuild_id = d.id\n" +
                "WHERE u.role_id = ?;";
        long count = 0;
        try {
            count = qr.query(conn, sql, new ScalarHandler<>(), dormManager);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return count;
    }

    /**
     * 学生的信息和ta所居住的宿舍信息的总条数
     *
     * @param student 生
     * @return 数据总条数
     */
    public long getAllStudentCount(Integer student) {
        Connection conn = getConn();
        String sql = "SELECT COUNT(*) FROM tb_user u LEFT JOIN tb_dormbuild d ON u.dormBuildId = d.id WHERE u.role_id = ?;";
        long count = 0;
        try {
            count = qr.query(conn, sql, new ScalarHandler<>(), student);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return count;
    }


    /**
     * 向表tb_user添加用户
     *
     * @param username       用户名
     * @param password       密码
     * @param sex            性别
     * @param tel            电话
     * @param role           角色id
     * @param create_user_id 创建该用户的用户id
     * @param nickname       昵称
     */
    public void addDormManager(String username, String password, String sex, String tel, Integer role, Integer create_user_id, String nickname) {
        Connection conn = getConn();
        String sql = "INSERT INTO tb_user(id,`name`,`passWord`,stu_code,sex,tel,role_id,create_user_id) VALUES(?,?,?,?,?,?,?,?);";
        try {
            qr.update(conn, sql,
                    null,
                    nickname,
                    password,
                    username,
                    sex,
                    tel,
                    role,
                    create_user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    /**
     * 向表tb_user添加用户
     *
     * @param nickname    姓名
     * @param username    用户名/学号
     * @param password    密码
     * @param sex         性别
     * @param tel         联系方式
     * @param build       所住宿舍楼
     * @param dormCode    寝室号
     * @param role        角色
     * @param currLoginId 当前登录的对象id
     */
    public void addStudent(String nickname, String username, String password, String sex, String tel, Integer build, String dormCode, Integer role, Integer currLoginId) {
        Connection conn = getConn();
        String sql = "INSERT INTO tb_user (id,`name`,`passWord`,stu_code,dorm_Code,sex,tel,dormBuildId,role_id,create_user_id)\n" +
                "VALUES(?,?,?,?,?,?,?,?,?,?);";
        try {
            qr.update(conn, sql,
                    null,
                    nickname,
                    password,
                    username,
                    dormCode,
                    sex,
                    tel,
                    build,
                    role,
                    currLoginId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    /**
     * 通过用户名查询用户的id
     *
     * @param username 用户名
     * @return id
     */
    public Integer getDormManagerIdByUsername(String username) {
        Connection conn = getConn();
        String sql = "select id from tb_user where stu_code = ?";
        Integer id = 0;
        try {
            id = qr.query(conn, sql, new ScalarHandler<>(), username);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return id;
    }

    /**
     * 添加宿舍管理员和其管理宿舍的映射
     *
     * @param id      id
     * @param buildId 宿舍楼id
     */
    public void addDormBuildMapper(Integer id, String buildId) {
        Connection conn = getConn();
        String sql = "INSERT INTO tb_manage_dormbuild VALUES(?,?,?);";
        try {
            qr.update(conn, sql,
                    null,
                    id,
                    buildId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    public UserAndBuildPlus getDormById(Integer id) {
        Connection conn = getConn();
        String sql = "select u.id,u.`name`,u.stu_code,u.`passWord`,u.sex,u.tel,GROUP_CONCAT(d.`name`) buildings\n" +
                "from tb_user u \n" +
                "LEFT JOIN tb_manage_dormbuild md on u.id = md.user_id\n" +
                "LEFT JOIN tb_dormbuild d on md.dormBuild_id = d.id\n" +
                "WHERE u.role_id = 1\n" +
                "GROUP BY u.id\n" +
                "HAVING u.id = ?\n";
        UserAndBuildPlus user = null;
        try {
            user = qr.query(conn, sql, new BeanHandler<>(UserAndBuildPlus.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return user;
    }

    /**
     * 通过id获取学生信息
     *
     * @param id id
     * @return 查询到的学生信息和对应的实体类
     */
    public Student getStudentById(Integer id) {
        Connection conn = getConn();
        String sql = "SELECT u.id,u.`name`,u.`passWord`,u.stu_code,u.dorm_Code,u.sex,u.tel,u.disabled,d.`name` buildName,d.remark \n" +
                "FROM tb_user u LEFT JOIN tb_dormbuild d ON u.dormBuildId = d.id\n" +
                "WHERE u.id = ?;\n";
        Student student = null;
        try {
            student = qr.query(conn, sql, new BeanHandler<>(Student.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return student;
    }

    /**
     * 通过id修改表tb_user,修改角色为宿舍管理员的信息
     *
     * @param id
     * @param username
     * @param password
     * @param sex
     * @param tel
     * @param nickname
     * @param role
     */
    public void updateDormManager(Integer id, String username, String password, String sex, String tel, String nickname, Integer role) {
        Connection conn = getConn();
        String sql = "UPDATE tb_user SET `name` = ?,stu_code = ?,`passWord` = ?,sex = ?,tel = ? WHERE id = ? AND role_id = ?";
        try {
            qr.update(conn, sql,
                    nickname,
                    username,
                    password,
                    sex,
                    tel,
                    id,
                    role);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    /**
     * 通过id修改表tb_user,修改角色为学生的信息
     *
     * @param id
     * @param nickname
     * @param username
     * @param password
     * @param dormCode
     * @param buildId
     * @param sex
     * @param tel
     * @param role
     */
    public void updateStudent(Integer id, String nickname, String username, String password, String dormCode, Integer buildId, String sex, String tel, Integer role) {
        Connection conn = getConn();
        String sql = "UPDATE tb_user SET `name` = ?,stu_code = ?,`passWord` = ?,dorm_Code = ?,dormBuildId = ?,sex = ?,tel = ?\n" +
                "WHERE id = ?;";
        try {
            qr.update(conn, sql,
                    nickname,
                    username,
                    password,
                    dormCode,
                    buildId,
                    sex,
                    tel,
                    id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    public void deleteDormBuildMapperByUserId(Integer id) {
        Connection conn = getConn();
        String sql = "DELETE FROM tb_manage_dormbuild WHERE user_id = ?";
        try {
            qr.update(conn, sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    /**
     * 通过id删除表tb_user内的数据
     *
     * @param id id
     */
    public void deleteUserById(Integer id) {
        Connection conn = getConn();
        String sql = "DELETE FROM tb_user WHERE id = ?";
        try {
            qr.update(conn, sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }


    public long countAllBuildList() {
        Connection conn = getConn();
        String sql = "SELECT count(id) FROM tb_dormbuild;";
        long count = 0;
        try {
            count = qr.query(conn, sql, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return count;
    }

    /**
     * 向表tb_dormbuild添加一条数据
     *
     * @param name
     * @param remark
     */
    public void addBuild(String name, String remark) {
        Connection conn = getConn();
        String sql = "insert into tb_dormbuild values(?,?,?,?)";
        try {
            qr.update(conn, sql,
                    null,
                    name,
                    remark,
                    1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    public DormBuild getBuildById(Integer id) {
        Connection conn = getConn();
        String sql = "SELECT id,`name`,remark,disabled FROM tb_dormbuild where id = ?;";
        DormBuild build = null;
        try {
            build = qr.query(conn, sql, new BeanHandler<>(DormBuild.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return build;
    }

    public void updateBuild(Integer id, String name, String remark) {
        Connection conn = getConn();
        String sql = "update tb_dormbuild set `name` = ?,remark = ? where id = ?";
        try {
            qr.update(conn, sql,
                    name,
                    remark,
                    id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    public void deleteBuildById(Integer id) {
        Connection conn = getConn();
        String sql = "delete from tb_dormbuild where id = ?";
        try {
            qr.update(conn, sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    /**
     * 查询所有学生的缺勤记录
     *
     * @param student  角色-学生
     * @param start    分页开始
     * @param pageSize 页内数据条数
     * @return 学生的缺勤记录实体类集合
     */
    public List<RecordPlus> getAllRecordList(Integer student, Integer start, Integer pageSize) {
        Connection conn = getConn();
        String sql = "SELECT u.id uid,r.id rid,r.date,u.stu_code,u.`name`,u.sex,d.`name` buildName,u.dorm_Code,r.remark\n" +
                "FROM tb_record r LEFT JOIN tb_user u ON r.student_id = u.id\n" +
                "LEFT JOIN tb_dormbuild d ON d.id = u.dormBuildId\n" +
                "WHERE u.role_id = ?\n" +
                "ORDER BY r.id\n" +
                "LIMIT ?,?;\n";
        List<RecordPlus> list = null;
        try {
            list = qr.query(conn, sql, new BeanListHandler<>(RecordPlus.class),
                    student,
                    start,
                    pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return list;
    }

    public long countAllRecordList(Integer student) {
        Connection conn = getConn();
        String sql = "SELECT COUNT(r.id) \n" +
                "FROM tb_record r \n" +
                "LEFT JOIN tb_user u ON r.student_id = u.id\n" +
                "LEFT JOIN tb_dormbuild d ON d.id = u.dormBuildId\n" +
                "WHERE u.role_id = ?;";
        long count = 0;
        try {
            count = qr.query(conn, sql, new ScalarHandler<>(), student);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return count;
    }

    public Integer getStudentIdByStuCodeAndRole(String stuCode, Integer student) {
        Connection conn = getConn();
        String sql = "select id from tb_user where stu_code = ? and role_id = ?";
        Integer id = 0;
        try {
            id = qr.query(conn, sql, new ScalarHandler<>(), stuCode, student);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return id;
    }

    public void addRecord(Integer student_id, String date, String remark) {
        Connection conn = getConn();
        String sql = "insert into tb_record values(?,?,?,?,?)";
        try {
            qr.update(conn, sql,
                    null,
                    student_id,
                    date,
                    remark,
                    1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    public RecordPlus getRecordByRid(Integer role_id, Integer rid) {
        Connection conn = getConn();
        String sql = "SELECT u.id uid,r.id rid,r.date,u.stu_code,u.`name`,u.sex,d.`name` buildName,u.dorm_Code,r.remark\n" +
                "FROM tb_record r LEFT JOIN tb_user u ON r.student_id = u.id\n" +
                "LEFT JOIN tb_dormbuild d ON d.id = u.dormBuildId\n" +
                "WHERE u.role_id = ? AND r.id = ?;";
        RecordPlus recordPlus = null;
        try {
            recordPlus = qr.query(conn, sql, new BeanHandler<>(RecordPlus.class), role_id, rid);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return recordPlus;
    }

    public void updateRecord(Integer rid, Integer student_id, String date, String remark) {
        Connection conn = getConn();
        String sql = "update tb_record set student_id = ?,date = ?,remark = ? where id = ?";
        try {
            qr.update(conn, sql,
                    student_id,
                    date,
                    remark,
                    rid);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    /**
     * 通过id删除tb_record的一条表记录
     *
     * @param id
     */
    public void deleteRecordById(Integer id) {
        Connection conn = getConn();
        String sql = "delete from tb_record where id = ?";
        try {
            qr.update(conn, sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    public List<RecordPlus> getMyAllRecordList(Integer id, Integer student, Integer start, Integer pageSize) {
        Connection conn = getConn();
        String sql = "SELECT u.id uid,r.id rid,r.date,u.stu_code,u.`name`,u.sex,d.`name` buildName,u.dorm_Code,r.remark\n" +
                "FROM tb_record r LEFT JOIN tb_user u ON r.student_id = u.id\n" +
                "LEFT JOIN tb_dormbuild d ON d.id = u.dormBuildId\n" +
                "WHERE u.role_id = ? and u.id = ?\n" +
                "ORDER BY r.id\n" +
                "LIMIT ?,?;";
        List<RecordPlus> list = null;
        try {
            list = qr.query(conn, sql, new BeanListHandler<>(RecordPlus.class),
                    student,
                    id,
                    start,
                    pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return list;
    }

    public long countMyAllRecordList(Integer id, Integer student) {
        Connection conn = getConn();
        String sql = "SELECT COUNT(r.id) \n" +
                "FROM tb_record r \n" +
                "LEFT JOIN tb_user u ON r.student_id = u.id\n" +
                "LEFT JOIN tb_dormbuild d ON d.id = u.dormBuildId\n" +
                "WHERE u.role_id = ? and u.id = ?;";
        long count = 0;
        try {
            count = qr.query(conn, sql, new ScalarHandler<>(), student, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return count;
    }
}
