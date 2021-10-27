package com.fqy.eduservice.service;

import com.fqy.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fqy.eduservice.entity.vo.CourseInfoVo;
import com.fqy.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-09-04
 */
public interface EduCourseService extends IService<EduCourse> {
    //添加课程的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);
}
