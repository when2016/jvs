package com.bctools.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.bctools.auth.mapper.UserPermissionMapper;
import com.bctools.auth.entity.UserPermission;
import com.bctools.auth.service.UserPermissionService;
import org.springframework.stereotype.Service;

/**
 * 用户角色
 *
 * @author
 */
@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements UserPermissionService {

}
