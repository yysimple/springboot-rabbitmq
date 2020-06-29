package cn.huntercat.service.impl;

import cn.huntercat.model.Permission;
import cn.huntercat.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wuchengxing
 * @version 1.0
 * @date 2019/12/13 10:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionServiceImplTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void findPermissionByUserId() {
        List<Permission> permissions = permissionService.findPermissionByUserId("1");
        System.out.println(permissions);
    }
}