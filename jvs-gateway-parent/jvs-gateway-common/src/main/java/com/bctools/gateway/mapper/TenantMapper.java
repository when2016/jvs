package com.bctools.gateway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bctools.gateway.entity.TenantPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公司租户管理
 *
 * @author guojing
 */
@Mapper
public interface TenantMapper extends BaseMapper<TenantPo> {

}
