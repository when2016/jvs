package com.jvs.log.event;

import com.jvs.log.po.LogPo;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author Administrator
 */
@Getter
public class LogApplicationEvent extends ApplicationEvent {

    private LogPo logPo;

    public LogApplicationEvent(Object source, LogPo logPo) {
        super(source);
        this.logPo = logPo;
    }
}
