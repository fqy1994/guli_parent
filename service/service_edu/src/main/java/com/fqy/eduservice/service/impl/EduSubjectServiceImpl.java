package com.fqy.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fqy.eduservice.entity.EduSubject;
import com.fqy.eduservice.entity.excel.SubjectData;
import com.fqy.eduservice.entity.subject.OneSubject;
import com.fqy.eduservice.entity.subject.TwoSubject;
import com.fqy.eduservice.listener.SubjectExcelListener;
import com.fqy.eduservice.mapper.EduSubjectMapper;
import com.fqy.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    //获取数据封装成前端需要的格式
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询所有一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);//也可以用这个this.list(wrapperOne)

        //查询所有二级分类,ne不等于0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);//也可以用这个this.list(wrapperOne)
        //创建list集合，用于存储最终封装数据
        ArrayList<OneSubject> finalSubjectList = new ArrayList<>();

        //封装一级分类
        //把查询出来所有的一级分类list集合遍历，得到每一个一级分类对象，获取每个一级分类
        //封装到要求的list集合里面
        for (int i = 0; i < oneSubjectList.size(); i++) {//可以用foreach 遍历oneSubjectList集合
            EduSubject eduSubject = oneSubjectList.get(i);

            //把edusubject里面值获取出来，放到OneSubject对象里面

            //多个OneSubject放到fiinalSubjectList里面

            OneSubject oneSubject = new OneSubject();
             /*复杂方法
            oneSubject.setId(eduSubject.getId());
            oneSubject.setTitle(eduSubject.getTitle());
            */
            BeanUtils.copyProperties(eduSubject,oneSubject);




            //在一级分类循环遍历查询所有的二级分类
            //创建list集合封装每个一级分类的二级分类
            ArrayList<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            //遍历二级分类list集合，如果有多个就要遍历多次，能否有更好的方法
            //另一种方法可以通过一级分类的id去查二级分类的id直接放进去
            //一般情况下复杂查询更耗资源
            for (int m = 0; m < twoSubjectList.size(); m++) {
                //获取每个二级分类
                EduSubject tSubject = twoSubjectList.get(m);
                if (tSubject.getParentId().equals(eduSubject.getId())){
                    //把tSubjcet值复制到Twosubject里面，放到twoFinalSubjectList里卖弄
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);


                }

            }
            oneSubject.setChildren(twoFinalSubjectList);
            finalSubjectList.add(oneSubject);
        }


        // 封装二级分类
        return  finalSubjectList;
    }
}
