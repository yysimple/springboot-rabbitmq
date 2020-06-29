package com.jxkj.mapper;

import com.jxkj.entity.SysUser;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
public interface UserMapper {

    /**
     * 通过用户名返回对象
     *
     * @param username
     * @return
     */
    @Select("select * from sys_user where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.jxkj.mapper.RoleMapper.getRolesByUserId"))
    })
    SysUser findByUsername(String username);
}
