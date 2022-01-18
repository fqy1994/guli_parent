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
 * 任务表
 * </p>
 *
 * @author fan_jane
 * @since 2022-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TTask对象", description="任务表")
public class TTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务id: 格式 yyyyMMddxxxxxx，长度14位, 支持每日最多生成999999个任务")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "任务类型: type=1001")
    private String taskType;

    @ApiModelProperty(value = "任务状态: type=1002")
    private String taskStatus;

    @ApiModelProperty(value = "任务开始时间，精确到时")
    private Date startTime;

    @ApiModelProperty(value = "任务截止时间，精确到时")
    private Date endTime;

    @ApiModelProperty(value = "接受期限，精确到时")
    private Date receiveTime;

    @ApiModelProperty(value = "召回期限，精确到时")
    private Date recallTime;

    @ApiModelProperty(value = "巡检员id")
    private String inspectorId;

    @ApiModelProperty(value = "养护工程师id")
    private String createBy;

    @ApiModelProperty(value = "逻辑删, 0未删, 1已删")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
