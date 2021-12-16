package com.jvs.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jvs.auth.entity.User;
import com.jvs.auth.entity.UserTenant;
import com.jvs.auth.mapper.UserExtensionMapper;
import com.jvs.auth.mapper.UserMapper;
import com.jvs.auth.mapper.UserRoleMapper;
import com.jvs.auth.mapper.UserTenantMapper;
import com.jvs.auth.service.UserService;
import com.jvs.common.exception.BusinessException;
import com.jvs.common.utils.TenantContextHolder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 用户服务
 *
 * @author
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    UserMapper userMapper;
    UserRoleMapper userRoleMapper;
    UserTenantMapper userTenantMapper;
    UserExtensionMapper userExtensionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User saveUser(User user, UserTenant userTenant) {
        String phone = user.getPhone();
        int count = this.count(Wrappers.<User>lambdaQuery().eq(User::getPhone, phone));
        if (count > 0) {
            throw new BusinessException("手机号已存在");
        }
        // 保存用户
        this.save(user);
        userTenant.setUserId(user.getId());
        userTenantMapper.insert(userTenant);
        return this.getById(user.getId());
    }

    @Override
    public User info(String username) {
        log.debug("用户名密码登录, 用户名: {}, 选择登录租户id: {}", username, TenantContextHolder.getTenantId());
        User user = this.getOne(Wrappers.<User>lambdaQuery().eq(User::getAccountName, username));
        if (ObjectUtil.isEmpty(user)) {
            throw new BusinessException("用户不存在或密码错误");
        }
        // 是否锁定
        if (user.getCancelFlag()) {
            // 所有用户都被禁用时
            throw new BusinessException("用户已被注销, 请联系管理员");
        }
        return user;
    }

    @Override
    public List<User> getByRoleIds(List<String> roleIds) {
        if (ObjectUtils.isEmpty(roleIds)) {
            // 角色id集合为空
            return Collections.emptyList();
        }
        StringBuilder builder = new StringBuilder("(");
        for (String roleId : roleIds) {
            if (StringUtils.isBlank(roleId)) {
                // 角色id为null
                return Collections.emptyList();
            }
            builder.append(roleId).append(",");
        }
        // 删除末尾的逗号
        builder.deleteCharAt(builder.length() - 1);
        builder.append(")");
        List<User> userList = userMapper.getByRoleIds(builder.toString());
        if (userList.isEmpty()) {
            return Collections.emptyList();
        }
        return userList;
    }

    @Override
    public List<User> getQueryDeptName(String deptName) {
        return userMapper.getQueryDeptName(deptName);
    }

    @Override
    public User phone(String phone) {
        //找到用户名和密码都匹配的
        User user = getOne(Wrappers.<User>lambdaQuery().eq(User::getPhone, phone));
        if (ObjectUtil.isEmpty(user)) {
            throw new BusinessException("用户不存在");
        }
        if (user.getCancelFlag()) {
            throw new BusinessException("用户已被注销, 请联系管理员");
        }
        return user;
    }

    @Override
    public <T> boolean updateInfo(SFunction<User, T> getter, T value, String userId) {
        if (Objects.isNull(getter) || StringUtils.isBlank(userId)) {
            return false;
        }
        return this.update(Wrappers.<User>lambdaUpdate().set(getter, value).eq(User::getId, userId));
    }

}
