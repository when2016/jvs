package com.jvs.auth.service.impl;

import com.jvs.auth.entity.Bulletin;
import com.jvs.auth.mapper.BulletinMapper;
import com.jvs.auth.service.BulletinService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description 系统公告 服务实现类
 * @author zhuxiaokang
 * @since 2021-11-19
 */
@Service
public class BulletinServiceImpl extends ServiceImpl<BulletinMapper, Bulletin> implements BulletinService {

}
