package com.bctools.document.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import com.bctools.common.exception.BusinessException;
import com.bctools.common.utils.R;
import com.bctools.document.component.RoleComponent;
import com.bctools.document.entity.DcLibrary;
import com.bctools.document.enums.OperationEnum;
import com.bctools.document.service.DcLibraryService;
import com.bctools.document.vo.req.ShareCheckReqVo;
import com.bctools.document.vo.req.ShareSaveReqVo;
import com.bctools.document.vo.res.ShareCheckResVo;
import com.bctools.log.annotation.Log;
import com.bctools.oauth2.utils.UserCurrentUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZhuXiaoKang
 * @Description: 知识库分享
 */

@Slf4j
@Api(tags = "分享")
@RestController
@RequestMapping(value = "/dcLibrary/share")
@AllArgsConstructor
public class ShareController {

    private final RoleComponent roleComponent;
    private final DcLibraryService dcLibraryService;

    @Log
    @ApiOperation(value = "分享设置")
    @PostMapping("/setting")
    public R<DcLibrary> shareSetting(@RequestBody @Validated ShareSaveReqVo shareSaveReqVo) {
        // 操作权限校验
        roleComponent.checkOperationAuthority(OperationEnum.SHARE_SETTING, UserCurrentUtils.getUserId(), shareSaveReqVo.getId());
        return R.ok(dcLibraryService.settingShare(shareSaveReqVo));
    }

    @Log
    @ApiOperation(value = "校验分享链接")
    @PostMapping("/check")
    public R<ShareCheckResVo> share(@RequestBody @Validated ShareCheckReqVo reqVo) {
        if (StringUtils.isBlank(reqVo.getKey())) {
            throw new BusinessException("分享链接key不能为空");
        }
        return R.ok(dcLibraryService.checkShare(reqVo));
    }
}
