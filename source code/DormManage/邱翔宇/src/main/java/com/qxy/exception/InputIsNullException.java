package com.qxy.exception;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 10:41
 * @Introduction: 用户名和密码有一个为空异常
 */
public class InputIsNullException extends RuntimeException {
    private static final long serialVersionUID = 6405475536762490795L;

    public InputIsNullException() {
        super("有必填选项输入为空");
    }
}
