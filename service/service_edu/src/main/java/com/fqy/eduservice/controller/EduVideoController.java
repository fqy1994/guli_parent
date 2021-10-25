package com.fqy.eduservice.controller;


import com.fqy.commonutils.R;
import com.fqy.eduservice.entity.EduVideo;
import com.fqy.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-09-04
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    //注入service
    @Autowired
    private EduVideoService eduVideoService;

    //添加小节接口
    @ApiOperation(value = "添加小节")//swagger注释
    @PostMapping("addViedo")//链接传输
    public R addVideo(@RequestBody EduVideo eduVideo){//获取前端传过来的对象
        eduVideoService.save(eduVideo);//调用service本来就有的save添加方法
        return R.ok();//返回成功的信息 R.ok就直接是个对象了，因为在R的一个静态方法里，就直接new了一个对象


    }

    //删除小节接口
    //TODO 需完善删除小节同时删除时评
    @ApiOperation(value = "删除小节接口")//添加swagger注释
    @DeleteMapping("{id}")//这个注解直接就是删除，在占位符中传入id
    public R deleteVideo(@PathVariable String id){ //获取接口占位符
        eduVideoService.removeById(id);//调用service原本就有的删除方法
        return R.ok();//返回成功的信息

    }

}

