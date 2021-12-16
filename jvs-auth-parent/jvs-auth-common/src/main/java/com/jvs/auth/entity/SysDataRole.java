package com.jvs.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.jvs.common.entity.dto.DataScopeDto;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author guojing
 */
@Data
@Accessors(chain = true)
@TableName(value = "sys_data_role", autoResultMap = true)
public class SysDataRole {

    @TableId(type = IdType.ASSIGN_UUID)
    String roleId;
    String dataId;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    DataScopeDto remark;
}