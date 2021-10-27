package com.fqy.eduservice.entity.vo;

import lombok.Data;

/**
 * @author fan_jennifer
 * @create 2021-10-2021/10/27 18:58
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price; //只用显示

}
