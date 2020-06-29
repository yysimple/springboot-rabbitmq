package cn.huntercat.service.impl;

import cn.huntercat.model.Permission;
import cn.huntercat.model.Role;
import cn.huntercat.model.RolePermission;
import cn.huntercat.model.UserRole;
import cn.huntercat.repository.PermissionRepository;
import cn.huntercat.repository.RolePermissionRepository;
import cn.huntercat.repository.RoleRepository;
import cn.huntercat.repository.UserRoleRepository;
import cn.huntercat.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/13 10:47
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    /*@Autowired
    private RoleRepository roleRepository;*/

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> findPermissionByUserId(String userId) {
        UserRole userRole = userRoleRepository.findUserRoleByUserId(userId);
        // Role role = roleRepository.findRoleById(userRole.getRoleId());
        RolePermission rolePermission = rolePermissionRepository.findRolePermissionByRoleId(userRole.getRoleId());
        List<Permission> permissions = permissionRepository.findPermissionById(rolePermission.getPermissionId());
        return permissions;
    }
}
