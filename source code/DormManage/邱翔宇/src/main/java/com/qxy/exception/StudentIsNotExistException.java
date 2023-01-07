package com.qxy.exception;

/**
 * @Author: SayHello
 * @Date: 2023/1/5 1:19
 * @Introduction:
 */
public class StudentIsNotExistException extends RuntimeException {
    private static final long serialVersionUID = -639472585259900043L;

    public StudentIsNotExistException() {
        super("学号无效,该学生不存在");
    }
}
