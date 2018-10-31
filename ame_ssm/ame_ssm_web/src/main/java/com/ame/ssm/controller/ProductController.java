package com.ame.ssm.controller;

import com.ame.ssm.domain.Product;
import com.ame.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;

    /**
     * 查询全部产品
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mav = new ModelAndView("product-list");
        List<com.ame.ssm.domain.Product> list = service.findAll();
        mav.addObject("productList",list);
        return mav;
    }

    /**
     * 产品添加
     * @param product
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        service.save(product);
        return "redirect:findAll.do";
    }
}
