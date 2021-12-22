package com.bctools.auth.feign;

import com.bctools.auth.api.api.AuthRoleServiceApi;
import com.bctools.auth.api.dto.SysRoleDto;
import com.bctools.auth.entity.Role;
import com.bctools.auth.service.RoleService;
import com.bctools.common.utils.BeanCopyUtil;
import com.bctools.common.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 */
@RequestMapping
@RestController
@AllArgsConstructor
public class RoleApiImpl implements AuthRoleServiceApi {

    RoleService roleService;

    @Override
    public R<List<SysRoleDto>> queryAll() {
        List<Role> list = roleService.list();
        List<SysRoleDto> copys = BeanCopyUtil.copys(list, SysRoleDto.class);
        return R.ok(copys);
    }

    @Override
    public R<SysRoleDto> getById(String id) {
        Role byId = roleService.getById(id);
        return R.ok(BeanCopyUtil.copy(byId, SysRoleDto.class));
    }

    @Override
    public R<List<SysRoleDto>> getByIds(List<String> ids) {
        List<Role> list = roleService.listByIds(ids);
        List<SysRoleDto> copys = BeanCopyUtil.copys(list, SysRoleDto.class);
        return R.ok(copys);
    }

}
