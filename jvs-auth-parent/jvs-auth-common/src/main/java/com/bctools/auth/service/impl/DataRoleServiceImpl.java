package com.bctools.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bctools.auth.comparator.UriPatternComparator;
import com.bctools.auth.entity.SysData;
import com.bctools.auth.entity.SysDataRole;
import com.bctools.auth.mapper.DataMapper;
import com.bctools.auth.mapper.DataRoleMapper;
import com.bctools.auth.service.DataRoleService;
import com.bctools.common.utils.BeanCopyUtil;
import com.bctools.common.entity.dto.DataApiDto;
import com.bctools.common.entity.dto.DataScopeDto;
import com.bctools.common.utils.ObjectNull;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author
 */
@Service
@AllArgsConstructor
public class DataRoleServiceImpl extends ServiceImpl<DataRoleMapper, SysDataRole> implements DataRoleService {

    DataMapper dataMapper;

    @Override
    public List<DataScopeDto> queryUserPermission(List<String> roleIds) {
        if (ObjectUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        List<SysDataRole> dataRoleList = this.list(Wrappers.<SysDataRole>lambdaQuery().in(SysDataRole::getRoleId, roleIds));
        if (ObjectUtils.isEmpty(dataRoleList)) {
            return Collections.emptyList();
        }
        Set<String> dataIds = dataRoleList.stream().map(SysDataRole::getDataId).collect(Collectors.toSet());
        // 获取资源路径信息
        List<SysData> sysDataList = dataMapper.selectBatchIds(dataIds);
        Map<String, SysData> sysDataMap = sysDataList.stream().collect(Collectors.toMap(SysData::getId, e -> e));
        // 封装数据权限信息集合
        List<DataScopeDto> dataScopeList = new ArrayList<>();
        for (SysDataRole dataRole : dataRoleList) {
            DataScopeDto scopeDto = dataRole.getRemark();
            SysData sysData = sysDataMap.get(dataRole.getDataId());
            if (ObjectNull.isNotNull(sysData)) {
                DataApiDto dataApiDto = BeanCopyUtil.copy(sysData, DataApiDto.class);
                if (StringUtils.isNotBlank(dataApiDto.getApi())) {
                    scopeDto.setDataApi(dataApiDto);
                    dataScopeList.add(scopeDto);
                }
            }
        }
        // 路径格式排序
        dataScopeList.sort((a, b) -> new UriPatternComparator().compare(a.getDataApi().getApi(), b.getDataApi().getApi()));
        return dataScopeList;
    }

}
