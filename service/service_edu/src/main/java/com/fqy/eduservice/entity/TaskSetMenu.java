package com.fqy.eduservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务套餐表
 * </p>
 *
 * @author fan_jane
 * @since 2022-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TaskSetMenu对象", description="任务套餐表")
public class TaskSetMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增长逐渐id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @ApiModelProperty(value = "逻辑删除:0 未删，1 删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建人")
    private String creatBy;

    @ApiModelProperty(value = "创建人")
    private String updateBy;

    private Date createTime;

    private Date updateTime;


}
