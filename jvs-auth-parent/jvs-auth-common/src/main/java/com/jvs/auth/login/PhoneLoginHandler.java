package com.jvs.auth.login;

import com.jvs.auth.component.SmsEmailComponent;
import com.jvs.auth.entity.User;
import com.jvs.auth.login.dto.PhoneDto;
import com.jvs.auth.service.UserExtensionService;
import com.jvs.auth.service.UserService;
import com.jvs.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Slf4j
@AllArgsConstructor
@Component("phone")
public class PhoneLoginHandler implements LoginHandler<PhoneDto> {

    UserExtensionService userExtensionService;
    SmsEmailComponent smsComponent;
    UserService userService;

    @Override
    public User handle(String code, String appId, PhoneDto phoneDto) {
        //校验是否成功
        smsComponent.check(phoneDto.getPhone(), phoneDto.getCode(), () -> new BusinessException("验证失败"));
        User user = userService.phone(phoneDto.getPhone());
        return user;
    }

    @Override
    public void bind(User user, String code, String appId) {

    }

}
