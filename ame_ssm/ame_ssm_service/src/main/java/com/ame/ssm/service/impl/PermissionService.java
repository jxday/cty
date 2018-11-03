package com.ame.ssm.service.impl;

import com.ame.ssm.dao.IPermissionDao;
import com.ame.ssm.domain.Permission;
import com.ame.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("permissionService")
@Transactional
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionDao dao;

    @Override
    public List<Permission> findAll() throws Exception {
        return dao.findAll();
    }

    @Override
    public void deleteById(String id) throws Exception {
        dao.deleteRole_PermissionByPermissionId(id);
        dao.deleteById(id);
    }

    @Override
    public void save(Permission permission) throws Exception {
        dao.save(permission);
    }
}
