package com.jvs.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jvs.auth.entity.UserExtension;
import com.jvs.auth.entity.UserTenant;
import com.jvs.auth.mapper.UserExtensionMapper;
import com.jvs.auth.mapper.UserTenantMapper;
import com.jvs.auth.service.UserExtensionService;
import com.jvs.auth.service.UserTenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class UserExtensionServiceImpl extends ServiceImpl<UserExtensionMapper, UserExtension> implements UserExtensionService {
}
