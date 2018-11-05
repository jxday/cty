package com.ame.ssm.controller;

import com.ame.ssm.domain.Role;
import com.ame.ssm.domain.UserInfo;
import com.ame.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

//    @RolesAllowed({"USER", "ADMIN"})
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mav = new ModelAndView("user-list");
        List<UserInfo> list = service.findAll();
        mav.addObject("userList",list);
        return mav;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        service.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception {
        UserInfo userInfo = service.findById(id);
        ModelAndView mav = new ModelAndView("user-show1");
        mav.addObject("user",userInfo);
        return mav;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception{
        ModelAndView mav = new ModelAndView("user-role-add");
        List<Role> list = service.findUserByIdAndAllRole(id);
        UserInfo user = service.findById(id);
        mav.addObject("roleList",list);
        mav.addObject("user",user);
        return mav;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name ="ids",required = true) String[] roleIds,String userId) throws Exception {
        for (String roleId:roleIds){
            service.addRoleToUser(roleId,userId);
        }
        return "redirect:findAll.do";
    }
}
