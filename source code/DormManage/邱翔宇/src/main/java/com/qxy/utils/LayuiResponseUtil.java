package com.qxy.utils;

import java.io.Serializable;

/**
 * @Author: SayHello
 * @Date: 2023/1/2 21:04
 * @Introduction: 工具类-统一设置layui的table组件的回复的数据格式
 */
public class LayuiResponseUtil<T> implements Serializable {
    private static final long serialVersionUID = 6060987508009130324L;
    /**
     * 默认状态码
     */
    private static final Integer CODE = 0;

    /**
     * 默认详细信息
     */
    private static final String MSG = "";

    /**
     * 自定义的状态码
     */
    private Integer code;

    /**
     * 自定义的详细信息
     */
    private String msg;

    /**
     * 数据总条数
     */
    private Long count;

    /**
     * 数据使用fastjson或其它工具转换成的json字符串
     */
    private T data;


    /**
     * 该方法用于将数据库查询到的数据,将其格式化成layui的table组件默认规定的数据格式
     *
     * @param code  状态码
     * @param msg   详细信息
     * @param data  数据
     * @param count 数据的总条数
     * @param <T>   数据的泛型
     * @return 返回一个符合layui的table组件规定的数据格式
     */
    public static <T> LayuiResponseUtil<T> pattern(Integer code, String msg, T data, Long count) {
        if (code == null && msg == null) {
            return result(CODE, MSG, data, count);
        } else {
            return result(code, msg, data, count);
        }
    }

    /**
     * 该方法用于处理数据返回规定的对象
     *
     * @param code  状态码
     * @param msg   详细信息
     * @param data  数据
     * @param count 数据的总条数
     * @param <T>   数据的泛型
     * @return 返回一个符合table组件规定的数据格式
     */
    private static <T> LayuiResponseUtil<T> result(Integer code, String msg, T data, Long count) {
        LayuiResponseUtil<T> response = new LayuiResponseUtil<>();
        response.setData(data);
        response.setCode(CODE);
        response.setMsg(MSG);
        response.setCount(count);
        return response;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LayuiResponseUtil{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
