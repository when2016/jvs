//package cn.bctools.document.aop;
//
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import cn.bctools.document.entity.DcLibrary;
//import cn.bctools.document.service.DcLibraryService;
//import cn.bctools.document.vo.BaseReqVo;
//import cn.bctools.upms.core.utils.UserCurrentUtils;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Optional;
//
///**
// * @Author: ZhuXiaoKang
// * @Description: 知识库AOP
// */
//@Component
//@Slf4j
//@Aspect
//public class DcLibraryAop {
//
//    @Autowired
//    private DcLibraryService dcLibraryService;
//
//    @Pointcut("execution(* cn.bctools.document.controller.DcLibraryController.*(..)) &&( @annotation(org.springframework.web.bind.annotation.PostMapping)" +
//            "||@annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
//            "||@annotation(org.springframework.web.bind.annotation.PutMapping)" +
//            "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
//            ")")
//    public void tenantPointcat() {
//
//    }
//
//    /**
//     * 用于设定当前线程后续执行使用的租户id
//     *
//     * @param point
//     * @return
//     */
//    @SneakyThrows
//    @Around("tenantPointcat()")
//    public Object around(ProceedingJoinPoint point) {
//        long startTime = System.currentTimeMillis();
//        // 若未登录，低代码会默认租户id为1。
//        // 知识库需要支持忽略租户执行sql，如（搜索[登录和未登录的查询范围不同]、完全开放的知识库查询、通过分享链接访问数据等）。
//        // 逻辑：
//        // 1.得到线程中的tenantId
//        // 2.若用户未登录，则清空tenantId
//        // 3.根据前端传递的入参，判断是否要查询知识库id所属租户id，来覆盖tenantId。
//        String tenantId = TenantContextHolder.getTenantId();
//        TenantContextHolder.clear();
//        try {
//            UserCurrentUtils.getCurrentUser();
//        } catch (Exception e) {
//            tenantId = null;
//        }
//        log.debug("当前租户id：{}", tenantId);
//
//        // 操作的知识库，可能不是当前登录用户所在租户的知识库，或者未登录访问，则根据知识库id，得到知识库所属租户id，并覆盖线程中的tenantId。避免查询不到数据
//        Object[] args = point.getArgs();
//        for (Object arg : args) {
//            if (arg instanceof BaseReqVo) {
//                JSONObject jsonObject = JSONUtil.parseObj(arg);
//                Boolean sameTenant = jsonObject.getBool(Get.name(BaseReqVo::getSameTenant));
//                String dcId = jsonObject.getStr(Get.name(BaseReqVo::getDcId));
//                if (sameTenant != null && Boolean.FALSE.equals(sameTenant) && StringUtils.isNotBlank(dcId)) {
//                    DcLibrary dcLibrary = dcLibraryService.getOne(Wrappers.<DcLibrary>lambdaQuery().eq(DcLibrary::getId, dcId));
//                    tenantId = Optional.ofNullable(dcLibrary).map(DcLibrary::getTenantId).orElse(tenantId);
//                }
//            }
//        }
//
//        // 若租户id不为空，则设置租户id
//        if (StringUtils.isNotBlank(tenantId)) {
//            TenantContextHolder.setTenantId(tenantId);
//        }
//        log.debug("使用的租户id：{}", TenantContextHolder.getTenantId());
//
//
//        Object obj = point.proceed();
//        log.debug("请求{}耗时{}ms",  Optional.ofNullable(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()).map(HttpServletRequest::getRequestURI).orElse(""), (System.currentTimeMillis() - startTime));
//        return obj;
//    }
//}
