package cn.edu.cqupt.wyglzx.model;

import cn.edu.cqupt.wyglzx.entity.AdminEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**
 * Created by cc on 16/5/29.
 */
public class AdminDetail extends User {

    private AdminEntity adminEntity;


    public AdminDetail(AdminEntity adminEntity) {
        super(adminEntity.getName(), adminEntity.getPassword(), AuthorityUtils.createAuthorityList(String.valueOf(adminEntity.getRole())));
        this.adminEntity = adminEntity;
    }

    public AdminEntity getAdmin() {
        return adminEntity;
    }

    public Long getId() {
        return adminEntity.getId();
    }

}
