package com.qxy.exception;

/**
 * @Author: SayHello
 * @Date: 2023/1/4 23:34
 * @Introduction: 宿舍楼已存在异常
 */
public class BuildIsExistException extends RuntimeException {
    private static final long serialVersionUID = 4284178250570207510L;

    public BuildIsExistException() {
        super("宿舍楼已存在");
    }
}
