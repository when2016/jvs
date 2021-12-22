package com.bctools.auth.login;

import com.bctools.auth.component.SmsEmailComponent;
import com.bctools.auth.entity.User;
import com.bctools.auth.login.dto.PhoneDto;
import com.bctools.auth.service.UserExtensionService;
import com.bctools.auth.service.UserService;
import com.bctools.common.exception.BusinessException;
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
