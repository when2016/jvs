package com.jvs.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jvs.log.po.LogPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 请求日志 Mapper 接口
 * </p>
 *
 * @author Administrator
 */
@Mapper
public interface SysLogDao extends BaseMapper<LogPo> {

}
