package com.bctools.auth.component;

import com.alibaba.fastjson.JSONObject;
import com.bctools.auth.entity.TenantPo;
import com.bctools.auth.entity.User;
import com.bctools.auth.login.LoginHandler;
import com.bctools.auth.service.TenantService;
import com.bctools.auth.service.UserService;
import com.bctools.common.utils.PasswordUtil;
import com.bctools.common.utils.TenantContextHolder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Optional;

/**
 * @author Administrator
 */
@Slf4j
@Component
@AllArgsConstructor
public class OtherAuthComponent {

    UserService userService;
    TenantService tenantService;
    Map<String, LoginHandler> handlerMap;

    /**
     * 根据标识获取用户
     *
     * @param type
     * @param code
     * @param appId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public User getUser(String type, String code, String appId) {
        String decodedPassword = PasswordUtil.decodedPassword(code, appId);
        LoginHandler loginHandler = handlerMap.get(type);
        Object obj = JSONObject.parseObject(decodedPassword, getParameterClass(loginHandler.getClass()));
        User handle = loginHandler.handle(code, appId, obj);
        return handle;
    }

    /**
     * 获取接口的泛型的 类
     */
    public Class getParameterClass(Class<?> e) {
        Type obj = e.getGenericInterfaces()[0];
        ParameterizedType parameterizedType = (ParameterizedType) obj;
        Class cls = (Class) parameterizedType.getActualTypeArguments()[0];
        return cls;
    }
}
