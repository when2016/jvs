package com.jvs.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jvs.auth.entity.LoginLog;

/**
 * @author dynamic-maven
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 获取上一次登录信息
     *
     * @param userId 用户Id
     * @return 用户登录日志信息
     */
    LoginLog getLastLoginInfo(String userId);

}
