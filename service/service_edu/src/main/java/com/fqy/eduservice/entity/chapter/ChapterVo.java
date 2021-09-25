package com.fqy.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fan_jennifer
 * @create 2021-09-2021/9/24 10:06
 */
@Data
public class ChapterVo {

    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}
