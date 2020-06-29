package cn.huntercat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/13 10:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_role_permission")
public class RolePermission {

    @Id
    private String roleId;

    private String permissionId;
}
