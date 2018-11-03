package com.ame.ssm.dao;

import com.ame.ssm.domain.Permission;
import com.ame.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;
// private String id;
//    private String roleName;
//    private String roleDesc;
//    private List<Permission> permissions;
//    private List<UserInfo> users;

public interface IRolesDao {
    @Select("select * from role where id in (select roleId from users_role where userId = #{id})")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = List.class,many = @Many(select = "com.ame.ssm.dao.IPermissionDao.findByRoleId")),
    })
    List<Role> findByUserId(String id) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = List.class,many = @Many(select = "com.ame.ssm.dao.IPermissionDao.findByRoleId")),})
    Role findById(String id) throws Exception;

    @Delete("delete from role_permission where roleId = #{id}")
    void deleteRole_PermissionById(String id);

    @Delete("delete from users_role where roleId = #{id}")
    void deleteUsers_RoleById(String id);

    @Delete("delete from role where id = #{id}")
    void deleteById(String id);
}
