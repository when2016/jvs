package com.jvs.dingding;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 钉钉，普通文本
 *
 * @author: GuoZi
 */
@Data
@AllArgsConstructor
public class Text {

    /**
     * 消息内容
     */
    private String content;

}
