package com.fqy.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.fqy.eduservice.entity.EduSubject;
import com.fqy.eduservice.entity.excel.SubjectData;
import com.fqy.eduservice.listener.SubjectExcelListener;
import com.fqy.eduservice.mapper.EduSubjectMapper;
import com.fqy.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-09-02
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //文件输入流，不同点是之前那个是写路径，这个是写流
            InputStream in = file.getInputStream();
            //调用方法并于实体类中的subjectDat对应，进行读取

            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();;

        }catch (Exception e){
            e.printStackTrace();

        }

    }
}
