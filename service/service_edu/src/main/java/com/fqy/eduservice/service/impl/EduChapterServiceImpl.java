package com.fqy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fqy.eduservice.entity.EduChapter;
import com.fqy.eduservice.entity.EduVideo;
import com.fqy.eduservice.entity.chapter.ChapterVo;
import com.fqy.eduservice.entity.chapter.VideoVo;
import com.fqy.eduservice.mapper.EduChapterMapper;
import com.fqy.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fqy.eduservice.service.EduVideoService;
import com.fqy.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-09-04
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1 根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> warpperChapter = new QueryWrapper<>();
        warpperChapter.eq("course_id", courseId);
        List<EduChapter> eduChaptersList = baseMapper.selectList(warpperChapter);


        //2 根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        warpperChapter.eq("course_id", courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);
        //创建list集合，用于最终封装数据
        List<ChapterVo> finalList = new ArrayList<>();


        //3 遍历查询章节list集合进行封装
        for (int i = 0; i < eduChaptersList.size(); i++) {
            EduChapter eduChapter = eduChaptersList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finalList.add(chapterVo);

            //创建集合，用于封装章节的小节
            List<VideoVo> videoList = new ArrayList<>();
            //遍历查询小节list集合，进行封装
            for (int j = 0; j < eduVideoList.size(); j++) {
                EduVideo eduVideo = eduVideoList.get(j);
                //判断：小节里面chapterid和章节里面id是否一样
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    //进行分装
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    //放到小节的封装的集合里面去
                    videoList.add(videoVo);
                }

            }
            //把封装之后小节lsit集合，放到章节对象里卖弄
            chapterVo.setChildren(videoList);


        }


        return finalList;
    }

    //删除章节的方法
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterid章节id，查询小结表，如果查出数据，不进行删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        //判断
        if (count > 0) {
            throw new GuliException(20001, "不能删除");
        } else {//不能查询数据，进行删除
            //删除章节
            int result = baseMapper.deleteById(chapterId);
            return result>0;


        }

    }
}
