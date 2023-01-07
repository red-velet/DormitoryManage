package com.qxy.exception;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 11:01
 * @Introduction:
 */
public class UsernameOrPasswordIncorrectException extends RuntimeException {
    private static final long serialVersionUID = -1099275094761696149L;

    public UsernameOrPasswordIncorrectException() {
        super("用户名/ 密码错误");
    }
}
