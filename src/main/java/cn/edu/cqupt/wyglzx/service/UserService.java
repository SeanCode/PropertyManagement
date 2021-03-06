package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.DepartmentDao;
import cn.edu.cqupt.wyglzx.dao.InstitutionDao;
import cn.edu.cqupt.wyglzx.dao.UserDao;
import cn.edu.cqupt.wyglzx.entity.DepartmentEntity;
import cn.edu.cqupt.wyglzx.entity.UserEntity;
import cn.edu.cqupt.wyglzx.exception.ExistsException;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cc on 16/6/25.
 */
@Component
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    InstitutionDao institutionDao;

    @Autowired
    DepartmentDao departmentDao;

    public UserEntity checkById(Long id) {
        UserEntity userEntity = userDao.getUserById(id);
        if (userEntity == null) {
            throw new NotExistsException();
        }
        return userEntity;
    }

    public void checkNotExistByIdCard(String idCard) {
        UserEntity userEntity = userDao.getUserByIdCard(idCard);
        if (userEntity != null) {
            throw new ExistsException();
        }
    }

    public List<UserEntity> getUserListByDepartmentId(Long departmentId) {
        List<UserEntity> users = userDao.getUserListByDepartmentId(departmentId);
        for (UserEntity user : users) {
            DepartmentEntity department = departmentDao.getDepartmentById(user.getDepartmentId());
            if (department != null) {
                user.setDepartmentName(department.getName());
            }
        }
        return users;
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

    public UserEntity addUser(String name, Long departmentId, String username, String phone, String idCard, String schoolCard, String remark) {
        checkNotExistByIdCard(idCard);
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setDepartmentId(departmentId);
        userEntity.setIdCard(idCard);
        if (StringUtils.isNotBlank(username)) {
            userEntity.setUsername(username);
        }
        if (StringUtils.isNotBlank(phone)) {
            userEntity.setPhone(phone);
        }
        if (StringUtils.isNotBlank(schoolCard)) {
            userEntity.setSchoolCard(schoolCard);
        }
        if (StringUtils.isNotBlank(remark)) {
            userEntity.setRemark(remark);
        }
        userEntity.setCreateTime(Util.time());
        userEntity.setUpdateTime(userEntity.getCreateTime());

        userEntity = userDao.save(userEntity);

        return userEntity;
    }

    public List<UserEntity> searchByName(String name) {
        if (name.length() > 0) {
            List<UserEntity> users = userDao.getUserListByNameLike(name);
            for (UserEntity user : users) {
                DepartmentEntity department = departmentDao.getDepartmentById(user.getDepartmentId());
                if (department != null) {
                    user.setDepartmentName(department.getName());
                }
            }
            return users;
        }

        return new ArrayList<>();
    }

    public void setDepartmentId(Long userId, Long departmentId) {
        UserEntity userEntity = checkById(userId);
        if (!userEntity.getDepartmentId().equals(departmentId)) {
            userEntity.setDepartmentId(departmentId);
            userEntity.setUpdateTime(Util.time());
            userDao.save(userEntity);
        }
    }

    public Integer getUserAmount() {

        return institutionDao.getInstitutionCount() + userDao.getUserAmount();
    }
}
