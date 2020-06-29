package com.jxkj.mapper;

import com.jxkj.entity.SysRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
public interface RoleMapper {

    /**
     * 根据用户id查询其对应所有的权限
     * @param userId
     * @return
     */
    @Select("select r.id id, r.role_name roleName, r.role_desc roleDesc from sys_role r, sys_user_role ur where r.id=ur.rid AND ur.uid=#{userId}")
    List<SysRole> getRolesByUserId(Integer userId);
}
