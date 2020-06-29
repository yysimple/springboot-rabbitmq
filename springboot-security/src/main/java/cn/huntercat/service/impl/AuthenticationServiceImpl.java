package cn.huntercat.service.impl;

import cn.huntercat.model.AuthenticationRequest;
import cn.huntercat.model.UserDto;
import cn.huntercat.service.AuthenticationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/11 11:01
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    /*@Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        if (null == authenticationRequest
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())) {
            throw new RuntimeException("账号或密码为空");
        }

        UserDto userDto = getUserDto(authenticationRequest.getUsername());
        if (null == userDto) {
            throw new RuntimeException("查询不到该用户");
        }
        if (!authenticationRequest.getPassword().equals(userDto.getPassword())) {
            throw new RuntimeException("账号或密码错误");
        }

        return userDto;
    }

    *//**
     * 模拟dao， 获取一个对象
     * @param username
     * @return
     *//*
    public UserDto getUserDto(String username){
        return userDtoMap.get(username);
    }

    // 用户信息
    private Map<String, UserDto> userDtoMap = new HashMap<>();
    {
        *//*Set<String> authorities1 = new HashSet<>();
        authorities1.add("p1");
        Set<String> authorities2 = new HashSet<>();
        authorities2.add("p2");*//*

        userDtoMap.put("zhangsan",new UserDto("1010", "zhangsan", "123", "张三", "133433"));
        userDtoMap.put("lisi", new UserDto("1011", "lisi", "456", "李四", "144599"));
    }
*/
}
