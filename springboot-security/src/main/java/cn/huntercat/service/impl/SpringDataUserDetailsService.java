package cn.huntercat.service.impl;

import cn.huntercat.model.Permission;
import cn.huntercat.model.UserDto;
import cn.huntercat.repository.UserDtoRepository;
import cn.huntercat.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/11 16:48
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDtoRepository userDtoRepository;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = userDtoRepository.getUserDtoByUsername(username);
        if (null == userDto) {
            return null;
        }
        // 查出用户的权限
        List<Permission> permissions = permissionService.findPermissionByUserId(userDto.getId());
        // 将permissions转换成数组
        String[] permissionArray = new String[permissions.size()];

        permissions.toArray(permissionArray);
        // 就是数据库中查询出来的对象
        UserDetails userDetails = User.withUsername(userDto.getUsername()).password(userDto.getPassword()).authorities(permissionArray).build();
        System.out.println(userDetails);
        return userDetails;
    }
}
