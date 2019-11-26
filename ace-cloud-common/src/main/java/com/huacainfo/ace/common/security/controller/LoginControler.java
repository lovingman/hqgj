package com.huacainfo.ace.common.security.controller;

import com.huacainfo.ace.common.dto.ResponseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginControler {
    @RequestMapping("/login")
    public String login() {
        return "/security/login";
    }

    @RequestMapping("/logout")
    public ResponseDTO logout() {
        ResponseDTO rst = new ResponseDTO();

        return rst;
    }

}
