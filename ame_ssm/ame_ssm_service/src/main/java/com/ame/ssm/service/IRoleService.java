package com.ame.ssm.service;


import com.ame.ssm.domain.Role;

import java.util.List;

public interface IRoleService{
    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(String id) throws Exception;

    void deleteById(String id) throws Exception;
}
