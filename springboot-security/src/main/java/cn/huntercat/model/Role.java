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
 * @date 2019/12/13 9:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_role")
public class Role {

    @Id
    private String id;

    private String roleName;

    private String description;

    private Date createTime;

    private Date updateTime;

    private Character status;
}
