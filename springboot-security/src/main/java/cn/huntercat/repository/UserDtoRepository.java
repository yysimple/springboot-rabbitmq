package cn.huntercat.repository;

import cn.huntercat.model.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/12 11:06
 */
@Repository
public interface UserDtoRepository extends JpaRepository<UserDto, String> {

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    UserDto getUserDtoByUsername(String username);

}
