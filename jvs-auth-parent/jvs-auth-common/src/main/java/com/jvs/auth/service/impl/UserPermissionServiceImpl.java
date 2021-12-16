package com.jvs.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jvs.auth.mapper.UserPermissionMapper;
import com.jvs.auth.entity.UserPermission;
import com.jvs.auth.service.UserPermissionService;
import org.springframework.stereotype.Service;

/**
 * 用户角色
 *
 * @author
 */
@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements UserPermissionService {

}
