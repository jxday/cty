package com.ame.ssm.service;

import com.ame.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll() throws Exception;

    void deleteById(String id) throws Exception;

    void save(Permission permission) throws Exception;
}
