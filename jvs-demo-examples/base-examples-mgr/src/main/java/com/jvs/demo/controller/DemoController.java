package com.jvs.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jvs.common.exception.BusinessException;
import com.jvs.common.utils.PasswordUtil;
import com.jvs.common.utils.R;
import com.jvs.common.entity.dto.UserDto;
import com.jvs.demo.entity.DemoPo;
import com.jvs.demo.mapper.DemoPoMapper;
import com.jvs.log.annotation.Log;
import com.jvs.oauth2.utils.AuthorityManagementUtils;
import com.jvs.oauth2.utils.UserCurrentUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping
@Api(tags = "test")
@AllArgsConstructor
public class DemoController {
    DemoPoMapper demoPoMapper;

    @Log
    @ApiOperation("测试")
    @GetMapping("/index")
    public R test(R userDto) {
        UserDto currentUser = UserCurrentUtils.getCurrentUser();
        AuthorityManagementUtils.getUserById("1");
        if (1 == 1) {
            throw new BusinessException("fdasf");
        }
        return R.ok(currentUser);
    }

    @Log
    @ApiOperation("测试数据权限分页")
    @GetMapping("/page")
    public R page(Page<DemoPo> poPage) {
        Page<DemoPo> demoPoPage = demoPoMapper.selectPage(poPage, Wrappers.query());
        return R.ok(demoPoPage);
    }

}
