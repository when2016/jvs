package com.bctools.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bctools.auth.entity.UserExtension;
import com.bctools.auth.entity.UserTenant;
import com.bctools.auth.mapper.UserExtensionMapper;
import com.bctools.auth.mapper.UserTenantMapper;
import com.bctools.auth.service.UserExtensionService;
import com.bctools.auth.service.UserTenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class UserExtensionServiceImpl extends ServiceImpl<UserExtensionMapper, UserExtension> implements UserExtensionService {
}
