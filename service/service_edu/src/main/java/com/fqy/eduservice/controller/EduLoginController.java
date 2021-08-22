package com.fqy.eduservice.controller;

import com.fqy.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author fan_jennifer
 * @create 2021-08-2021/8/22 16:02
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/user")
public class EduLoginController {

    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");

    }
}
