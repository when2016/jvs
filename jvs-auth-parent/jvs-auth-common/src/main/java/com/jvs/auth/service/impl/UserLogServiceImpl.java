package com.jvs.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jvs.auth.entity.UserMessageLog;
import com.jvs.auth.mapper.UserMessageLogMapper;
import com.jvs.auth.service.UserMessageLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author guojing
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserLogServiceImpl extends ServiceImpl<UserMessageLogMapper, UserMessageLog> implements UserMessageLogService {

}
