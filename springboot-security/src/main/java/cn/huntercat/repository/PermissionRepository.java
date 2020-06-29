package cn.huntercat.repository;

import cn.huntercat.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/13 10:36
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

    List<Permission> findPermissionById(String permissionId);
}
