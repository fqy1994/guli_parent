package com.fqy.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author fan_jennifer
 * @create 2021-08-2021/8/29 17:13
 */
public interface OssService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);

}
