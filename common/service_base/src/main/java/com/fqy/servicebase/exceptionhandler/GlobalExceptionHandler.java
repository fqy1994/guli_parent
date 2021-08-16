package com.fqy.servicebase.exceptionhandler;


import com.fqy.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fan_jennifer
 * @create 2021-08-2021/8/15 14:52
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据，因为不是在controller中
    public R erro(Exception e) {
        e.printStackTrace();
        return R.erro().message("执行了全局异常处理..");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//为了返回数据，因为不是在controller中
    public R erro(ArithmeticException e) {
        e.printStackTrace();
        return R.erro().message("执行ArithmeticException异常处理..");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody//为了返回数据，因为不是在controller中
    public R erro(GuliException e) {
        log.error(e.getMsg());
        e.printStackTrace();
        return R.erro().code(e.getCode()).message(e.getMsg());
    }




}
