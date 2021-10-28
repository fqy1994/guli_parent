package com.fqy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fqy.eduservice.entity.EduChapter;
import com.fqy.eduservice.entity.EduCourseDescription;
import com.fqy.eduservice.mapper.EduCourseDescriptionMapper;
import com.fqy.eduservice.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-09-04
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    @Override
    public void removeDescriptionById(String courseId) {
        QueryWrapper<EduCourseDescription> eduCourseDescriptionQueryWrapper = new QueryWrapper<>();
        eduCourseDescriptionQueryWrapper.eq("course_id",courseId);

        baseMapper.delete(eduCourseDescriptionQueryWrapper);
    }
}
