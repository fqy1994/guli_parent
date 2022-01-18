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
 * 
 * </p>
 *
 * @author fan_jane
 * @since 2022-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Task对象", description="")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "任务编码：暂时用X20220212-1类型，后期会改")
    private String code;

    @ApiModelProperty(value = "任务名称：某年某月某个时刻某个位置的任务")
    private String codeName;

    @ApiModelProperty(value = "匝道")
    private String ramp;

    @ApiModelProperty(value = "判断上下层：1上层 0下层")
    private String bridgeStorey;

    @ApiModelProperty(value = "起始桩号")
    private String stakeBegin;

    @ApiModelProperty(value = "终止桩号")
    private String stakeEnd;

    @ApiModelProperty(value = "任务类型: 1001000001 日常巡检 1001000002 定期检查 1001000003 特殊检查 1001000004 养护维修 1001000005 临时任务")
    private String taskType;

    @ApiModelProperty(value = "任务状态: 1002000001 待领取  1002000002  进行中  1002000003  待审核 1002000004 待分配 1002000005 待重传 1002000006 已完成 1002000007 自动召回 1002000008 主动召回 ")
    private String taskStatus;

    @ApiModelProperty(value = "巡检员")
    private String inspector;

    @ApiModelProperty(value = "巡检开始时间 ")
    private Date beginTime;

    @ApiModelProperty(value = "巡检结束时间 ")
    private Date endTime;

    @ApiModelProperty(value = "接受期限 ")
    private Date acceptDeadTime;

    @ApiModelProperty(value = "召回期限")
    private Date recallDeadTime;

    @ApiModelProperty(value = "逻辑删除:0 未删，1 删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建日期： 1000-01-01 00：00：00 ")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String creatBy;

    @ApiModelProperty(value = "修改日期： 1000-01-01 00：00：00 ")
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    private String updateBy;


}
