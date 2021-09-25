package com.fqy.eduservice.service;

import com.fqy.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fqy.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-09-04
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
