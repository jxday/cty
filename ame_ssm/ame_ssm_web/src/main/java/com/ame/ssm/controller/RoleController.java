package com.ame.ssm.controller;

import com.ame.ssm.domain.Role;
import com.ame.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService service;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Role> list = service.findAll();
        ModelAndView mav = new ModelAndView("role-list");
        mav.addObject("roleList",list);
        return mav;
    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        service.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        Role role = service.findById(id);
        ModelAndView mav = new ModelAndView("role-show");
        mav.addObject("role",role);
        return mav;
    }

    @RequestMapping("/deleteById.do")
    public String deleteById(String id) throws Exception{
        service.deleteById(id);
        return "redirect:findAll.do";
    }
}
