package cn.huntercat.model;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/11 10:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class UserDto {

    // public static final String SESSION_USER_KEY = "_user";

    @Id
    private String id;

    private String username;

    private String password;

    private String fullname;

    private String mobile;

    // private Set<String> authorities;
}
