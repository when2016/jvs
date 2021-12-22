package com.bctools.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bctools.auth.entity.SysData;
import com.bctools.auth.mapper.DataMapper;
import com.bctools.auth.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
@AllArgsConstructor
public class DataServiceImpl extends ServiceImpl<DataMapper, SysData> implements DataService {

}
