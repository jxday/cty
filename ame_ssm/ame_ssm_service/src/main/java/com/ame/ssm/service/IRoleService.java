package com.ame.ssm.service;


import com.ame.ssm.domain.Permission;
import com.ame.ssm.domain.Role;

import java.util.List;

public interface IRoleService{
    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(String id) throws Exception;

    void deleteById(String id) throws Exception;

    List<Permission> findRoleByIdAndAllPermission(String id) throws Exception;

    void addPermissionToRole(String permissionId, String roleId) throws Exception;
}
