package com.jvs.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jvs.auth.entity.Apply;
import com.jvs.auth.mapper.ApplyMapper;
import com.jvs.auth.service.ApplyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
@AllArgsConstructor
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {
    @Override
    public Apply loadClientByClientId(String clientId) {
        return getOne(Wrappers.query(new Apply().setAppKey(clientId)));
    }
}
