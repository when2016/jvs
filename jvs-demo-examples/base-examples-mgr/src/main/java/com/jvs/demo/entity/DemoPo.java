package com.jvs.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jvs.database.annotation.JvsDataTable;
import com.jvs.database.annotation.JvsDataTableField;
import com.jvs.database.entity.po.BasePo;
import com.jvs.demo.entity.enums.TypeEnum;
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
