package com.bctools.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bctools.auth.entity.UserMessageLog;
import com.bctools.auth.mapper.UserMessageLogMapper;
import com.bctools.auth.service.UserMessageLogService;
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
