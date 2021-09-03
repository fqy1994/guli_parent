package com.fqy.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fqy.eduservice.entity.EduSubject;
import com.fqy.eduservice.entity.excel.SubjectData;
import com.fqy.eduservice.service.EduSubjectService;
import com.fqy.servicebase.exceptionhandler.GuliException;

/**
 * @author fan_jennifer
 * @create 2021-09-2021/9/2 16:52
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //不能交给spring进行管理，因为需要自己new对象，不能注入其他对象
    //想要调service的方法可能调不到，因为没有交给sping管理
    //没法使用@autowired注入，所以需要手动注入service
    // 不能实现数据库操作，因为不能使用mapper需要手动写jdbc
    public EduSubjectService subjectService;
    public SubjectExcelListener(){}
    public SubjectExcelListener(EduSubjectService subjectService){
        this.subjectService = subjectService;
    }

    //读取方法
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw new GuliException(20001,"文件数据为空");
        }

        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //读到第一个值家到数据库中，第二个值加到数据库中

        //添加一级分类
        //判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if(existOneSubject == null){//没有相同一级分类进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());//一级分类名称
            subjectService.save(existOneSubject);
        }

        //拿到一级分类的id值
        String pid = existOneSubject.getId();

        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject= this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if(existTwoSubject == null){//没有相同一级分类进行添加
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//一级分类名称
            subjectService.save(existTwoSubject);
        }

    }
    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();

        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);//这个方法好不好啊，和数据库交互这么多
        return  oneSubject;

    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();

        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);//这个方法好不好啊，和数据库交互这么多
        return twoSubject;

    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
