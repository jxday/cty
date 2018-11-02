package com.ame.ssm.dao;

import com.ame.ssm.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRolesDao {
    @Select("select * from role where id in (select ROLEID from users_role where USERID = #{id})")
    List<Role> findByUserId(String id);
}
