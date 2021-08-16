package com.fqy.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author fan_jennifer
 * @create 2021-08-2021/8/15 17:57
 */
@Data
@NoArgsConstructor//sheng
@AllArgsConstructor
public class GuliException extends RuntimeException{
    private Integer code;//状态码
    private String msg;//异常信息
}
