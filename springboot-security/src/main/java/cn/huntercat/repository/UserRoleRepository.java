package cn.huntercat.repository;

import cn.huntercat.model.Permission;
import cn.huntercat.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/13 10:36
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    UserRole findUserRoleByUserId(String userId);
}
