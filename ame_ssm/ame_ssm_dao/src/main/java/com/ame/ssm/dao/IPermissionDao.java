package com.ame.ssm.dao;

import com.ame.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id})")
    List<Permission> findByRoleId(String id) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Delete("delete from role_permission where permissionId = #{id}")
    void deleteRole_PermissionByPermissionId(String id);

    @Delete("delete from permission where id = #{id}")
    void deleteById(String id);

    @Insert("insert into permission(permissionName,URL) values(#{permissionName},#{URL})")
    void save(Permission permission);

}
