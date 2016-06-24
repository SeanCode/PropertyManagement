package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.PasswordHash;
import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.AdminDao;
import cn.edu.cqupt.wyglzx.entity.AdminEntity;
import cn.edu.cqupt.wyglzx.exception.ExistsException;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import cn.edu.cqupt.wyglzx.exception.WrongPasswordException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by cc on 16/5/28.
 */
@Component
public class AdminService {

    @Autowired
    AdminDao adminDao;

    public AdminEntity login(String name, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        AdminEntity admin = adminDao.existByName(name);
        if (admin == null || !PasswordHash.validatePassword(password, admin.getPassword())) {
            throw new WrongPasswordException();
        }
        return admin;
    }

    public AdminEntity addAdmin(String name, String password, String username, Integer privilege) throws InvalidKeySpecException, NoSuchAlgorithmException {
        AdminEntity admin = adminDao.existByName(name);
        if (admin != null) {
            throw new ExistsException();
        }
        admin = new AdminEntity();
        admin.setName(name);
        admin.setPassword(PasswordHash.createHash(password));
        if (StringUtils.isNotBlank(username)) {
            admin.setUsername(username);
        }
        if (privilege > 0) {
            admin.setPrivilege(privilege);
        }
        admin.setCreateTime(Util.time());
        admin.setUpdateTime(admin.getCreateTime());

        admin = adminDao.save(admin);
        return admin;
    }

    public AdminEntity updateAdminByRoot(String name, String newUserName, String newName, int newPrivilege) throws InvalidKeySpecException, NoSuchAlgorithmException {

        AdminEntity admin = adminDao.existByName(name);
        if (admin == null) {
            throw new NotExistsException();
        }
        if (adminDao.existByName(newName) != null) {
            throw new ExistsException();
        }
        if (StringUtils.isNotBlank(newUserName)) {
            admin.setUsername(newUserName);
        }
        if (StringUtils.isNotBlank(newName)) {
            admin.setName(newName);
        }
        if (newPrivilege >= 0) {
            admin.setPrivilege(newPrivilege);
        }
        admin.setUpdateTime(Util.time());

        admin = adminDao.save(admin);
        return admin;
    }

    public AdminEntity updateAdmin(String name, String password, String newUserName, String newName) throws InvalidKeySpecException, NoSuchAlgorithmException {

        AdminEntity admin = adminDao.existByName(name);
        if (admin == null) {
            throw new NotExistsException();
        }
        if (!PasswordHash.validatePassword(password, admin.getPassword())) {
            throw new WrongPasswordException();
        }
        if (adminDao.existByName(newName) != null) {
            throw new ExistsException();
        }
        if (StringUtils.isNotBlank(newUserName)) {
            admin.setUsername(newUserName);
        }
        if (StringUtils.isNotBlank(newName)) {
            admin.setName(newName);
        }
        admin.setUpdateTime(Util.time());

        admin = adminDao.save(admin);
        return admin;
    }

    public AdminEntity updatePassword(String name, String password, String oldPassword) throws InvalidKeySpecException, NoSuchAlgorithmException {

        AdminEntity admin = adminDao.existByName(name);
        if (admin == null) {
            throw new NotExistsException();
        }
        if (!PasswordHash.validatePassword(oldPassword, admin.getPassword())) {
            throw new WrongPasswordException();
        }
        admin.setPassword(PasswordHash.createHash(password));
        admin.setUpdateTime(Util.time());

        admin = adminDao.save(admin);
        return admin;
    }

    public AdminEntity resetPassword(String name, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {

        AdminEntity admin = adminDao.existByName(name);
        if (admin == null) {
            throw new NotExistsException();
        }
        admin.setPassword(PasswordHash.createHash(password));
        admin.setUpdateTime(Util.time());

        admin = adminDao.save(admin);
        return admin;
    }

    public List<AdminEntity> getList() {
        return adminDao.list();
    }

}
