package com.qxy.exception;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 15:35
 * @Introduction:
 */
public class UserIsExistException extends RuntimeException {
    private static final long serialVersionUID = 6313563278618578803L;

    public UserIsExistException() {
        super("用户已存在");
    }
}
