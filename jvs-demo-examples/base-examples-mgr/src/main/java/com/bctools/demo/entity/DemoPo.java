package com.bctools.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bctools.database.annotation.JvsDataTable;
import com.bctools.database.annotation.JvsDataTableField;
import com.bctools.database.entity.po.BasePo;
import com.bctools.demo.entity.enums.TypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Administrator
 */
@Data
@Accessors(chain = true)
@TableName("demo")
@JvsDataTable(value = "demo", desc = "客户管理")
public class DemoPo extends BasePo {

    @TableId(type = IdType.ASSIGN_UUID)
    String id;
    /**
     * 类型，数据权限标记
     */
    @JvsDataTableField(name = "type", desc = "示例自定义类型")
    TypeEnum type;

}
