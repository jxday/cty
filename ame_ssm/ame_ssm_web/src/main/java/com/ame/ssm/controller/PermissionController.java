package com.ame.ssm.controller;

import com.ame.ssm.domain.Permission;
import com.ame.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService service;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Permission> list = service.findAll();
        ModelAndView mav = new ModelAndView("permission-list");
        mav.addObject("permission",list);
        return mav;
    }

    @RequestMapping("/deleteById.do")
    public String deleteById(String id) throws Exception{
        service.deleteById(id);
        return "redirect:findAll.do";
    }

    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        service.save(permission);
        return "redirect:findAll.do";
    }
}
