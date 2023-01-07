package com.qxy.exception;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 15:27
 * @Introduction:
 */
public class InputException extends RuntimeException {
    private static final long serialVersionUID = 2078136851937392855L;

    public InputException() {
        super("用户名/密码输入格式不正确 | 两次输入密码不一致");
    }
}
