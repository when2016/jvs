package com.jvs.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jvs.auth.entity.Apply;
import com.jvs.auth.entity.Dept;

import java.util.List;

/**
 * @author
 */
public interface ApplyService extends IService<Apply> {

    /**
     * 查询应用客户端是否存在
     *
     * @param clientId
     * @return
     */
    Apply loadClientByClientId(String clientId);

}
