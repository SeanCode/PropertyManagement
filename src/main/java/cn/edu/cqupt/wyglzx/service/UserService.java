package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.UserDao;
import cn.edu.cqupt.wyglzx.entity.UserEntity;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import org.apache.commons.lang3.StringUtils;
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

    public UserEntity checkById(Long id) {
        UserEntity userEntity = userDao.getUserById(id);
        if (userEntity == null) {
            throw new NotExistsException();
        }
        return userEntity;
    }

    public List<UserEntity> getUserListByDepartmentId(Long departmentId) {
        return userDao.getUserListByDepartmentId(departmentId);
    }

    public UserEntity getUserById(Long id) {
        return checkById(id);
    }

    public void deleteUser(Long id) {
        UserEntity userEntity = checkById(id);
        userEntity.setWeight(-1);
        userEntity.setUpdateTime(Util.time());

        userDao.save(userEntity);
    }

    public UserEntity updateUserInfo(Long id, String name, String username, String phone, String idCard, String schoolCard, String remark) {
        UserEntity userEntity = checkById(id);

        if (StringUtils.isNotBlank(name)) {
            userEntity.setName(name);
        }
        if (StringUtils.isNotBlank(username)) {
            userEntity.setUsername(username);
        }
        if (StringUtils.isNotBlank(phone)) {
            userEntity.setPhone(phone);
        }
        if (StringUtils.isNotBlank(idCard)) {
            userEntity.setIdCard(idCard);
        }
        if (StringUtils.isNotBlank(schoolCard)) {
            userEntity.setSchoolCard(schoolCard);
        }
        if (StringUtils.isNotBlank(remark)) {
            userEntity.setRemark(remark);
        }
        userEntity.setUpdateTime(Util.time());

        userEntity = userDao.save(userEntity);

        return userEntity;
    }

}
