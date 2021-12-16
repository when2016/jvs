package com.jvs.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jvs.auth.entity.Menu;
import com.jvs.auth.entity.Permission;
import com.jvs.auth.entity.RolePermission;
import com.jvs.auth.entity.enums.RolePermissionEnum;
import com.jvs.auth.mapper.RolePermissionMapper;
import com.jvs.auth.service.MenuService;
import com.jvs.auth.service.PermissionService;
import com.jvs.auth.service.RolePermissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色菜单基础服务
 *
 * @author Administrator
 */
@Slf4j
@Service
@AllArgsConstructor
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    MenuService menuService;
    PermissionService permissionService;

    @Override
    public List<Menu> getMenuRoleById(List<String> roleId) {
        //根据角色查询所有的菜单权限状态资源数据
        List<String> ids = getIds(RolePermissionEnum.menu, roleId);
        if (ObjectUtil.isNotEmpty(ids)) {
            List<Menu> menus = menuService.listByIds(ids);
            //只能查询启用的菜单
            return menus;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Permission> getFunctionRoleById(List<String> roleId) {
        List<String> ids = getIds(RolePermissionEnum.button, roleId);
        if (ObjectUtil.isEmpty(ids)) {
            return new ArrayList<>();
        }
        List<Permission> permissionData = permissionService.listByIds(ids).stream().distinct().collect(Collectors.toList());
        return permissionData;
    }

    protected List<String> getIds(RolePermissionEnum rolePermissionEnum, List<String> roleIds) {
        //根据角色查询所有的数据权限状态资源数据
        List<RolePermission> list = list(new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getType, rolePermissionEnum).in(RolePermission::getRoleId, roleIds));
        //获取到数据权限资源集
        List<String> dataPermissionIds = list.stream().map(RolePermission::getPermissionId).distinct().collect(Collectors.toList());
        return dataPermissionIds;
    }

}
