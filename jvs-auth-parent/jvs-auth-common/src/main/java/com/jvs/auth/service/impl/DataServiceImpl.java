package com.jvs.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jvs.auth.entity.SysData;
import com.jvs.auth.mapper.DataMapper;
import com.jvs.auth.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
@AllArgsConstructor
public class DataServiceImpl extends ServiceImpl<DataMapper, SysData> implements DataService {

}
