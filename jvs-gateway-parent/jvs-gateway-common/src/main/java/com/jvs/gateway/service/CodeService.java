package com.jvs.gateway.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jvs.common.utils.R;
import com.jvs.gateway.entity.GatewayCodePo;
import com.jvs.gateway.mapper.GatewayCodeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Slf4j
@Service
@AllArgsConstructor
public class CodeService {

    GatewayCodeMapper gatewayCodeMapper;

    /**
     * 置换错误消息信息
     *
     * @param r
     */
    public void transformCode(R r) {
        String msg = r.getMsg();
        GatewayCodePo gatewayCodePo = gatewayCodeMapper.selectOne(Wrappers.query(new GatewayCodePo().setMsg(msg)));
        if (ObjectUtil.isNotEmpty(gatewayCodePo)) {
            //更新msg的消息内容
            r.setMsg(gatewayCodePo.getMsg());
        }
    }


}
