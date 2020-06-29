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
 * @date 2019/12/13 9:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_permission")
public class Permission {

    @Id
    private String id;

    private String code;

    private String description;

    private String url;
}
