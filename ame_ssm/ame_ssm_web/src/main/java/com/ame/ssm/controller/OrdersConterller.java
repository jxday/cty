package com.ame.ssm.controller;

import com.ame.ssm.domain.Orders;
import com.ame.ssm.service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersConterller {

    @Autowired
    private IOrdersService service;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        List<Orders> list = service.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        ModelAndView mav = new ModelAndView("orders-list");
        mav.addObject("pageInfo",pageInfo);
        return mav;
    }
}
