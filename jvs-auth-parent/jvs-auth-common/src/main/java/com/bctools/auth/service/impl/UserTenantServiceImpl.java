package com.bctools.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bctools.auth.entity.User;
import com.bctools.auth.entity.UserTenant;
import com.bctools.auth.mapper.UserTenantMapper;
import com.bctools.auth.service.UserTenantService;
import com.bctools.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class UserTenantServiceImpl extends ServiceImpl<UserTenantMapper, UserTenant> implements UserTenantService {

    @Override
    public void clearUser(@NotNull String userId) {
        this.update(Wrappers.<UserTenant>lambdaUpdate().set(UserTenant::getCancelFlag, true).eq(UserTenant::getUserId, userId));
    }

    @Override
    public UserTenant checkUserId(String userId) {
        UserTenant userTenant = this.getOne(Wrappers.<UserTenant>lambdaQuery().eq(UserTenant::getUserId, userId));
        if (Objects.isNull(userTenant)) {
            throw new BusinessException("该用户不存在");
        }
        return userTenant;
    }

}
