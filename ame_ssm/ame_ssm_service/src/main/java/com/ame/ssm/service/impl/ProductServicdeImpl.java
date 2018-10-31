package com.ame.ssm.service.impl;

import com.ame.ssm.dao.IProductDao;
import com.ame.ssm.domain.Product;
import com.ame.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServicdeImpl implements IProductService {
    @Autowired
    private IProductDao dao;

    @Override
    public List<Product> findAll() throws Exception {
        return dao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        dao.save(product);
    }
}
