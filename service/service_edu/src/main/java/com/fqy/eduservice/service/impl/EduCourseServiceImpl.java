package com.fqy.eduservice.service.impl;

import com.fqy.eduservice.entity.EduCourse;
import com.fqy.eduservice.entity.EduCourseDescription;
import com.fqy.eduservice.entity.vo.CourseInfoVo;
import com.fqy.eduservice.mapper.EduCourseMapper;
import com.fqy.eduservice.service.EduCourseDescriptionService;
import com.fqy.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqy.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-09-04
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //因为要向别的表操作，所以要用到别的表的service，用谁就注入谁
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

//添加课程信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            //添加失败
            throw new GuliException(20001,"添加课程信息失败");
        }
        //获取添加之后课程id
        String cid = eduCourse.getId();

        //向课程简介表添加课程简介
        EduCourseDescription courseDescription = new EduCourseDescription();
        //BeanUtils.copyProperties(courseInfoVo,courseDescription);
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;


    }

}
