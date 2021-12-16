package com.jvs.auth.component;

import com.jvs.auth.entity.LoginLog;
import com.jvs.auth.service.LoginLogService;
import com.jvs.common.entity.dto.UserDto;
import com.jvs.common.entity.dto.UserInfoDto;
import com.jvs.common.utils.BeanCopyUtil;
import com.jvs.common.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;

/**
 * <登录日志>
 *
 * @author auto
 **/
@Slf4j
public class LoginLogComponent {
    public static void successLog(Authentication authentication) {
        UserDto userDto = ((UserInfoDto) authentication.getPrincipal()).getUserDto();
        //登录成功日志记录
        LoginLog copy = BeanCopyUtil.copy(userDto, LoginLog.class);
        copy.setId(null);
        copy.setOperateTime(LocalDateTime.now());
        copy.setStatus(true).setUserId(userDto.getId());
        //直接保存用户登录信息
        SpringContextUtil.getBean(LoginLogService.class).save(copy);
    }


}
