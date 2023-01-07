package com.qxy.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 10:00
 * @Introduction: BasicDao用于获取数据库连接和关闭数据库连接
 */
public class BasicDao {
    /**
     * 初始化数据源
     */
    private static DataSource dataSource = null;

    static {
        final Properties properties = new Properties();
        try {
            //配置数据源
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 该方法用于获取数据库连接
     *
     * @return Connection
     */
    protected static Connection getConn() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 该方法用于关闭连接
     *
     * @param connection 数据库连接
     * @param statement  预处理
     * @param resultSet  结果集
     */
    protected static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
