package cn.huntercat.service;

import cn.huntercat.model.Permission;

import java.util.List;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/13 10:39
 */
public interface PermissionService {
    List<Permission> findPermissionByUserId(String userId);
}
