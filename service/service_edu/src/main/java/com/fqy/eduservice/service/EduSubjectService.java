package com.fqy.eduservice.service;

import com.fqy.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fqy.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-09-02
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);
    //课程列表树形结构
    List<OneSubject> getAllOneTwoSubject();
}
