package com.jvs.auth.feign;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jvs.auth.api.api.RegExpServiceApi;
import com.jvs.auth.api.dto.RegExpDto;
import com.jvs.auth.entity.SysRegExp;
import com.jvs.auth.service.SysRegExpService;
import com.jvs.common.utils.BeanCopyUtil;
import com.jvs.common.utils.R;
import com.jvs.log.annotation.Log;
import io.swagger.annotations.ApiOperation;
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
public class RegExpApiImpl implements RegExpServiceApi {

    SysRegExpService sysRegExpService;

    @Log
    @Override
    @ApiOperation(value = "分页查询所有正则")
    public R<List<RegExpDto>> list(String name) {
        LambdaQueryWrapper<SysRegExp> queryWrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(name)) {
            queryWrapper.like(SysRegExp::getName, name);
        }
        List<SysRegExp> list = sysRegExpService.list(queryWrapper);
        List<RegExpDto> copys = BeanCopyUtil.copys(list, RegExpDto.class);
        return R.ok(copys);
    }

    @Log
    @Override
    @ApiOperation(value = "根据正则名称查询字典")
    public R<RegExpDto> getByName(String uniqueName) {
        SysRegExp one = sysRegExpService.getOne(Wrappers.query(new SysRegExp().setUniqueName(uniqueName)).last("limit 1"));
        RegExpDto copy = BeanCopyUtil.copy(one, RegExpDto.class);
        return R.ok(copy);
    }

}
