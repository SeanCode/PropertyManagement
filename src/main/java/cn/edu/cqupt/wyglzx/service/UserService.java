package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.dao.UserDao;
import cn.edu.cqupt.wyglzx.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by cc on 16/6/25.
 */
@Component
public class UserService {

    @Autowired
    UserDao userDao;

    public List<UserEntity> getUserListByDepartmentId(Long departmentId) {
        return userDao.getUserListByDepartmentId(departmentId);
    }

}
