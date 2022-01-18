package com.fqy.eduservice.service.impl;

import com.fqy.eduservice.entity.Task;
import com.fqy.eduservice.mapper.TaskMapper;
import com.fqy.eduservice.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fan_jane
 * @since 2022-01-18
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

}
