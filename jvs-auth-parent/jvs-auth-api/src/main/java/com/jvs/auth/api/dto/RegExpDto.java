package com.jvs.auth.api.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author : GaoZeXi
 */
@Data
@ApiModel(value = "正则管理")
@Accessors(chain = true)
public class RegExpDto {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "唯一名称,该名称为该字典创建时name的值,且该值永不改变,作为该字典被引用的key")
    private String uniqueName;
    @ApiModelProperty(value = "名称")
    @NotNull(message = "名称不允许为空")
    @Size(max = 20, message = "名称最大长度为20")
    private String name;
    @ApiModelProperty(value = "表达式")
    @NotNull(message = "表达式不允许为空")
    @Size(max = 999, message = "表达式超出长度限制")
    private String expression;
    @ApiModelProperty(value = "自定义分类")
    @NotNull(message = "分类不允许为空")
    private String type;
    @ApiModelProperty(value = "备注信息")
    private String remarks;
}
