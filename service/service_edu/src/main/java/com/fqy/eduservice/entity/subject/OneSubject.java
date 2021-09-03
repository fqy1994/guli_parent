package com.fqy.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fan_jennifer
 * @create 2021-09-2021/9/3 20:15
 */
@Data
public class OneSubject {
    private String title;
    private String id;

    //一个一级分类有多个二级分类,能用递归封装吗
    private List<TwoSubject> children = new ArrayList<>();
}
