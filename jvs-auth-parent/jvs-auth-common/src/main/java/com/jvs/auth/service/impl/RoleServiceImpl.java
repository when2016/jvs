package com.jvs.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jvs.auth.entity.Role;
import com.jvs.auth.entity.enums.RoleTypeEnum;
import com.jvs.auth.api.enums.SysRoleEnum;
import com.jvs.auth.mapper.RoleMapper;
import com.jvs.auth.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色服务
 *
 * @author
 */
@Service
@AllArgsConstructor
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDefaultSysRole() {
        LocalDateTime now = LocalDateTime.now();
        List<Role> roleList = Arrays.stream(SysRoleEnum.values())
                .map(role -> new Role()
                        .setRoleName(role.getName())
                        .setRoleDesc(role.getDesc())
                        .setType(RoleTypeEnum.userRole)
                        .setCreateTime(now)
                        .setUpdateTime(now))
                .collect(Collectors.toList());
        this.saveBatch(roleList);
    }

}
