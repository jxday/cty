package com.ame.ssm.controller;

import com.ame.ssm.domain.SysLog;
import com.ame.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<SysLog> list = sysLogService.findAll();
        ModelAndView mav = new ModelAndView("syslog-list");
        mav.addObject("sysLogs",list);
        return mav;
    }
}
