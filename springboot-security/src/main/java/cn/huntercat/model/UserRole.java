package cn.huntercat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/13 10:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user_role")
public class UserRole {

    @Id
    private String userId;

    private String roleId;

    private Date createTime;

    private String creator;
}
