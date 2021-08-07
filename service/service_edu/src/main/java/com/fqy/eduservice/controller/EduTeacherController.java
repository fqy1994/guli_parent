package com.fqy.eduservice.controller;


import com.fqy.eduservice.entity.EduTeacher;
import com.fqy.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-07
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    //注入service
    @Autowired
    private EduTeacherService teacherService;

    //查询讲师表所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/All")
    public List<EduTeacher> findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return list;


    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "逻辑删除讲师")
    public boolean removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        return flag;
    }
}

