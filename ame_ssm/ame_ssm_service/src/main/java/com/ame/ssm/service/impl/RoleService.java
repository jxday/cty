package com.ame.ssm.service.impl;

import com.ame.ssm.dao.IRolesDao;
import com.ame.ssm.domain.Permission;
import com.ame.ssm.domain.Role;
import com.ame.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleService implements IRoleService {
    @Autowired
    private IRolesDao dao;

    @Override
    public List<Role> findAll() throws Exception {
        return dao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        dao.save(role);
    }

    @Override
    public Role findById(String id) throws Exception {
        return dao.findById(id);
    }

    @Override
    public void deleteById(String id) throws Exception {
        dao.deleteRole_PermissionById(id);
        dao.deleteUsers_RoleById(id);
        dao.deleteById(id);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String id) throws Exception {
        return dao.findRoleByIdAndAllPermission(id);
    }

    @Override
    public void addPermissionToRole(String permissionId, String roleId) throws Exception {
        dao.addPermissionToRole(permissionId,roleId);
    }
}
