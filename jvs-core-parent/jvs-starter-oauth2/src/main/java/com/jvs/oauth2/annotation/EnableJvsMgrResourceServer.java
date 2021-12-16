package com.jvs.oauth2.annotation;

import com.jvs.feign.annotation.EnableJvsFeignClients;
import com.jvs.oauth2.config.ClientFeignConfig;
import com.jvs.oauth2.config.JvsAdapter;
import com.jvs.oauth2.config.JvsUserDetailsServiceImpl;
import com.jvs.oauth2.prop.JvsOAuth2Property;
import com.jvs.oauth2.utils.AuthorityManagementUtils;
import com.jvs.oauth2.utils.ScannerApplicationContextAware;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableOAuth2Sso
@EnableResourceServer
@EnableJvsFeignClients
@Import({
        ClientFeignConfig.class,
        JvsAdapter.class,
        JvsUserDetailsServiceImpl.class,
        JvsOAuth2Property.class,
        AuthorityManagementUtils.class,
        ScannerApplicationContextAware.class
})
public @interface EnableJvsMgrResourceServer {

}
