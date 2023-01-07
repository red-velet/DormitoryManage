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
 * @Date: 2023/1/4 10:31
 * @Introduction:
 */
public class CommonDao extends BasicDao {
    QueryRunner qr = new QueryRunner();

    /**
     * 查找表tb_dormbuild的所有数据并返回
     *
     * @return
     */
    public List<DormBuild> getBuildingList() {
        Connection conn = getConn();
        String sql = "select id,name,remark,disabled from tb_dormbuild";
        List<DormBuild> list = null;
        try {
            list = qr.query(conn, sql, new BeanListHandler<>(DormBuild.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return list;
    }

    /**
     * 通过用用户名和密码进行查询,查询成功返回对应的实体类对象,失败返回空
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        Connection conn = getConn();
        String sql = "SELECT id,`name`,`passWord`,stu_code,dorm_Code,sex,tel,dormBuildId,role_id,create_user_id,disabled\n" +
                "FROM tb_user WHERE stu_code = ? AND `passWord` = ?";
        User user = null;
        try {
            user = qr.query(conn, sql, new BeanHandler<>(User.class), username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return user;
    }

    /**
     * 通过用户名和角色id检查用户是否已存在
     *
     * @param username 用户名
     * @param role     角色id
     * @return 存在返回真, 不存在返回假
     */
    public boolean checkUserIsExist(String username, Integer role) {
        Connection conn = getConn();
        String sql = "SELECT id from tb_user where stu_code = ? and role_id = ?";
        Integer id = null;
        try {
            id = qr.query(conn, sql, new ScalarHandler<>(), username, role);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return id != null;
    }


    public List<UserAndBuild> getDormManagerByNickname(String condition, Integer start, Integer pageSize) {
        Connection conn = getConn();
        String sql = "SELECT u.id,u.`name`,u.sex,u.tel,GROUP_CONCAT(td.`name`) buildings\n" +
                "FROM tb_user u \n" +
                "LEFT JOIN tb_manage_dormbuild tmd\n" +
                "\tON u.id = tmd.user_id\n" +
                "LEFT JOIN tb_dormbuild td \n" +
                "\tON tmd.dormBuild_id = td.id \n" +
                "WHERE u.role_id = 1\t\n" +
                "GROUP BY u.id\n" +
                "HAVING u.`name` LIKE ?\t\n" +
                "ORDER BY u.id\n" +
                "LIMIT ?,?;";
        List<UserAndBuild> list = null;
        try {
            list = qr.query(conn, sql, new BeanListHandler<>(UserAndBuild.class),
                    "%" + condition + "%",
                    start,
                    pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return list;
    }

    public long countDormManagerByNickname(String condition) {
        Connection conn = getConn();
        String sql = "SELECT COUNT(DISTINCT(u.id))\n" +
                "FROM tb_user u \n" +
                "LEFT JOIN tb_manage_dormbuild tmd\n" +
                "\tON u.id = tmd.user_id\n" +
                "LEFT JOIN tb_dormbuild td \n" +
                "\tON tmd.dormBuild_id = td.id \n" +
                "WHERE u.`name` LIKE ?\t;";
        long count = 0;
        try {
            count = qr.query(conn, sql, new ScalarHandler<>(), "%" + condition + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return count;
    }


    public List<UserAndBuild> getDormManagerBySex(String condition, Integer start, Integer pageSize) {
        Connection conn = getConn();
        String sql = "SELECT u.id,u.`name`,u.sex,u.tel,GROUP_CONCAT(td.`name`) buildings\n" +
                "FROM tb_user u \n" +
                "LEFT JOIN tb_manage_dormbuild tmd\n" +
                "\tON u.id = tmd.user_id\n" +
                "LEFT JOIN tb_dormbuild td \n" +
                "\tON tmd.dormBuild_id = td.id \n" +
                "WHERE u.role_id = 1\t\n" +
                "GROUP BY u.id\n" +
                "HAVING u.sex LIKE ?\n" +
                "ORDER BY u.id\n" +
                "LIMIT ?,?;\n";
        List<UserAndBuild> list = null;
        try {
            list = qr.query(conn, sql, new BeanListHandler<>(UserAndBuild.class),
                    "%" + condition + "%",
                    start,
                    pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return list;
    }

    public long countDormManagerBySex(String condition) {
        Connection conn = getConn();
        String sql = "SELECT COUNT(DISTINCT(u.id))\n" +
                "FROM tb_user u \n" +
                "LEFT JOIN tb_manage_dormbuild tmd\n" +
                "\tON u.id = tmd.user_id\n" +
                "LEFT JOIN tb_dormbuild td \n" +
                "\tON tmd.dormBuild_id = td.id \n" +
                "WHERE u.role_id = 1\tAND u.sex LIKE ?";
        long count = 0;
        try {
            count = qr.query(conn, sql, new ScalarHandler<>(),
                    "%" + condition + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return count;
    }

    /**
     * @param content
     * @param start
     * @param pageSize
     * @return
     */
    public List<Student> getStudentByNickname(String content, Integer start, Integer pageSize) {
        Connection conn = getConn();
        String sql = "SELECT u.id,u.`name`,u.`passWord`,u.stu_code,u.dorm_Code,u.sex,u.tel,u.disabled,d.`name` buildName,d.remark \n" +
                "FROM tb_user u LEFT JOIN tb_dormbuild d ON u.dormBuildId = d.id\n" +
                "WHERE u.role_id = 2 AND u.`name` LIKE ?\n" +
                "ORDER BY u.id\n" +
                "LIMIT ?,?;";
        List<Student> list = null;
        try {
            list = qr.query(conn, sql, new BeanListHandler<>(Student.class),
                    "%" + content + "%",
                    start,
                    pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return list;
    }

    public long countStudentByNickname(String content) {
        Connection conn = getConn();
        String sql = "SELECT count(*)\n" +
                "FROM tb_user u LEFT JOIN tb_dormbuild d ON u.dormBuildId = d.id\n" +
                "WHERE u.role_id = 2 AND u.`name` LIKE ?";
        long count = 0;
        try {
            count = qr.query(conn, sql, new ScalarHandler<>(), "%" + content + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return count;
    }

    public List<Student> getStudentBySex(String content, Integer start, Integer pageSize) {
        Connection conn = getConn();
        String sql = "SELECT u.id,u.`name`,u.`passWord`,u.stu_code,u.dorm_Code,u.sex,u.tel,u.disabled,d.`name` buildName,d.remark \n" +
                "FROM tb_user u LEFT JOIN tb_dormbuild d ON u.dormBuildId = d.id\n" +
                "WHERE u.role_id = 2 AND u.sex LIKE ?\n" +
                "ORDER BY u.id\n" +
                "LIMIT ?,?;";
        List<Student> list = null;
        try {
            list = qr.query(conn, sql, new BeanListHandler<>(Student.class),
                    "%" + content + "%",
                    start,
                    pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return list;
    }

    public long countStudentBySex(String content) {
        Connection conn = getConn();
        String sql = "SELECT COUNT(*) \n" +
                "FROM tb_user u LEFT JOIN tb_dormbuild d ON u.dormBuildId = d.id\n" +
                "WHERE u.role_id = 2 AND u.sex LIKE ?";
        long count = 0;
        try {
            count = qr.query(conn, sql, new ScalarHandler<>(), "%" + content + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return count;
    }

    public Integer checkBuildIsExist(String name) {
        Connection conn = getConn();
        String sql = "SELECT id from tb_dormbuild where name = ? ";
        Integer id = null;
        try {
            id = qr.query(conn, sql, new ScalarHandler<>(), name);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return id;
    }

    public List<DormBuild> getBuildByNickname(String content, Integer start, Integer pageSize) {
        Connection conn = getConn();
        String sql = "SELECT id,`name`,remark,disabled FROM tb_dormbuild \n" +
                "WHERE `name` LIKE ?\n" +
                "ORDER BY id LIMIT ?,?;";
        List<DormBuild> list = null;
        try {
            list = qr.query(conn, sql, new BeanListHandler<>(DormBuild.class),
                    "%" + content + "%",
                    start,
                    pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return list;
    }

    public long countBuildByNickname(String content) {
        Connection conn = getConn();
        String sql = "SELECT COUNT(id) FROM tb_dormbuild WHERE `name` LIKE ?;";
        long count = 0;
        try {
            count = qr.query(conn, sql, new ScalarHandler<>(), "%" + content + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return count;
    }

    /**
     * 按日期范围分页查找缺勤记录
     *
     * @param startTime 开始时间
     * @param endTime   截止时间
     * @param start     分页起始
     * @param pageSize  分页数据大小
     * @param role_id   角色id-学生
     * @return 查找到的缺勤记录实体类集合
     */
    public List<RecordPlus> getRecordByDate(String startTime, String endTime, Integer start, Integer pageSize, Integer role_id) {
        Connection conn = getConn();
        String sql = "SELECT u.id uid,r.id rid,r.date,u.stu_code,u.`name`,u.sex,d.`name` buildName,u.dorm_Code,r.remark\n" +
                "FROM tb_record r LEFT JOIN tb_user u ON r.student_id = u.id\n" +
                "LEFT JOIN tb_dormbuild d ON d.id = u.dormBuildId\n" +
                "WHERE u.role_id = ? AND r.date >= ? AND r.date <= ?\n" +
                "ORDER BY r.id\n" +
                "LIMIT ?,?;";
        List<RecordPlus> list = null;
        try {
            list = qr.query(conn, sql, new BeanListHandler<>(RecordPlus.class),
                    role_id,
                    startTime,
                    endTime,
                    start,
                    pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return list;
    }


    public long countRecordByDate(String startTime, String endTime, Integer student) {
        Connection conn = getConn();
        String sql = "SELECT COUNT(r.id)\n" +
                "FROM tb_record r LEFT JOIN tb_user u ON r.student_id = u.id\n" +
                "LEFT JOIN tb_dormbuild d ON d.id = u.dormBuildId\n" +
                "WHERE u.role_id = ? AND r.date >= ? AND r.date <= ?;\n";
        long count = 0;
        try {
            count = qr.query(conn, sql, new ScalarHandler<>(), student, startTime, endTime);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return count;
    }

    public void updateInfo(Integer id, String name, String stuCode, String password, String sex, String tel) {
        Connection conn = getConn();
        String sql = "UPDATE tb_user SET `name` = ?,stu_code = ?,`passWord` = ?,sex = ?,tel = ? WHERE id = ?;";
        try {
            qr.update(conn, sql,
                    name,
                    stuCode,
                    password,
                    sex,
                    tel,
                    id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    public List<RecordPlus> getMyRecordByDate(String startTime, String endTime, Integer start, Integer pageSize, Integer student, Integer id) {
        Connection conn = getConn();
        String sql = "SELECT u.id uid,r.id rid,r.date,u.stu_code,u.`name`,u.sex,d.`name` buildName,u.dorm_Code,r.remark\n" +
                "FROM tb_record r LEFT JOIN tb_user u ON r.student_id = u.id\n" +
                "LEFT JOIN tb_dormbuild d ON d.id = u.dormBuildId\n" +
                "WHERE u.role_id = ? and u.id = ? and r.date >= ?  and r.date <= ?\n" +
                "ORDER BY r.id\n" +
                "LIMIT ?,?;";
        List<RecordPlus> list = null;
        try {
            list = qr.query(conn, sql, new BeanListHandler<>(RecordPlus.class),
                    student,
                    id,
                    startTime,
                    endTime,
                    start,
                    pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return list;
    }


    public long countMyRecordByDate(Integer id, Integer student, String startTime, String endTime) {
        Connection conn = getConn();
        String sql = "SELECT COUNT(r.id)\n" +
                "FROM tb_record r LEFT JOIN tb_user u ON r.student_id = u.id\n" +
                "LEFT JOIN tb_dormbuild d ON d.id = u.dormBuildId\n" +
                "WHERE u.role_id = ? and u.id = ? and r.date >= ?  and r.date <= ?;";
        long count = 0;
        try {
            count = qr.query(conn, sql, new ScalarHandler<>(), student, id, startTime, endTime);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
        return count;
    }
}
