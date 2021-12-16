package com.jvs.document.component;

import com.jvs.common.exception.BusinessException;
import com.jvs.document.service.DcLibraryService;
import com.jvs.document.vo.req.ShareCheckReqVo;
import com.jvs.document.vo.res.ShareCheckResVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: ZhuXiaoKang
 * @Description: 分享校验
 */

@Slf4j
@Component
@AllArgsConstructor
public class ShareComponent {

    private DcLibraryService dcLibraryService;

    /**
     * 获取分享资源的id
     *
     * @param shareReqVo 分享请求
     * @return id
     */
    public String getId(ShareCheckReqVo shareReqVo) {
        ShareCheckResVo shareRes = dcLibraryService.checkShare(shareReqVo);
        if (Boolean.FALSE.equals(shareRes.getCheck())) {
            throw new BusinessException("已停止分享,或分享不存在");
        }
        return shareRes.getId();
    }

    /**
     * 校验分享资源
     *
     * @param shareReqVo 分享请求
     */
    public void checkShare(ShareCheckReqVo shareReqVo) {
        getId(shareReqVo);
    }
}
