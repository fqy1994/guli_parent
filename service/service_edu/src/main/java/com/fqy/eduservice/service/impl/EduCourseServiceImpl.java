package com.fqy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fqy.eduservice.entity.EduChapter;
import com.fqy.eduservice.entity.EduCourse;
import com.fqy.eduservice.entity.EduCourseDescription;
import com.fqy.eduservice.entity.EduVideo;
import com.fqy.eduservice.entity.vo.CourseInfoVo;
import com.fqy.eduservice.entity.vo.CoursePublishVo;
import com.fqy.eduservice.mapper.EduCourseMapper;
import com.fqy.eduservice.service.EduChapterService;
import com.fqy.eduservice.service.EduCourseDescriptionService;
import com.fqy.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqy.eduservice.service.EduVideoService;
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
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;


    //添加课程信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            //添加失败
            throw new GuliException(20001, "添加课程信息失败");
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

    //根据课程查id
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);

        //2 查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());


        return courseInfoVo;
    }

    //修改课程信息
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new GuliException(20001, "修改课程信息失败");
        }
        //修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);

    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo coursePublishVo = baseMapper.getPublishCourseInfo(id);

        return coursePublishVo;
    }

    @Override
    public void removeCourse(String courseId) {
        //删除小节
        //TODO 删除小节删除对应视频的文件还有事务要不要用
        eduVideoService.removeVideoByCourseId(courseId);


        //删除章节
        eduChapterService.removeChapterByCourseId(courseId);

        //删除描述
        courseDescriptionService.removeDescriptionById(courseId);

        //删除课程
        int result = baseMapper.deleteById(courseId);
        if (result==0){
            throw new GuliException(20001,"删除失败");

        }
    }

}
