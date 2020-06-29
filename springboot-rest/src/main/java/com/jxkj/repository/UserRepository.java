package com.jxkj.repository;

import com.jxkj.entity.User;
import com.jxkj.service.VirtualUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * excerptProjection = UserService.class:
 * 可以获取到 UserService 里面指定的字段
 * excerptProjection = VirtualUser.class
 * 可以获取虚拟列 name + age 的拼接
 */
@CrossOrigin
@RepositoryRestResource(path = "user",excerptProjection = VirtualUser.class)
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * name属性上忽略大小写，那么可以将方法签名改成如下
     * @param name
     * @return
     */
    @RestResource(path="nameStartsWith",rel="nameStartsWith")
    List<User> findByNameStartsWith(@Param("name") String name);

    /**
     * 需要匹配多个添加则用And和Or连接
     * @param name
     * @return
     */
    @RestResource(path="nameStartsWith",rel="nameStartsWith")
    List<User> findByNameStartsWithIgnoringCase(@Param("name") String name);

    /**
     * 可以在方法名称的结尾处添加OrderBy，实现结果集排序
     * @param name
     * @return
     */
    @RestResource(path="nameStartsWith",rel="nameStartsWith")
    List<User> findByNameStartsWithOrderByAgeDesc(@Param("name") String name);
}
