package com.jvs.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jvs.auth.entity.Dept;
import com.jvs.auth.entity.Role;
import com.jvs.auth.entity.User;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author
 */
public interface DeptService extends IService<Dept> {

    /**
     * 移除指定用户信息
     * <p>
     * 仅处理部门负责人信息
     *
     * @param userId 用户id
     */
    void clearUser(@NotNull String userId);

    /**
     * 校验部门id
     *
     * @param deptId 部门id
     * @return 部门信息
     */
    Dept checkId(String deptId);

}
