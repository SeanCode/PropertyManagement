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
        super(adminEntity.getName(), adminEntity.getPassword(), AuthorityUtils.createAuthorityList(String.valueOf(adminEntity.getPrivilege())));
        this.adminEntity = adminEntity;
    }

    public AdminEntity getAdmin() {
        return adminEntity;
    }

    public boolean hasAuthorizedInput() {
        return adminEntity.getAdminAuth().isInput();
    }

    public boolean hasAuthorizedCheck() {
        return adminEntity.getAdminAuth().isCheck();
    }

    public boolean hasAuthorizedStatistics() {
        return adminEntity.getAdminAuth().isStatistics();
    }

    public boolean hasAuthorizedOwner() {
        return adminEntity.getAdminAuth().isOwner();
    }

    public boolean hasAuthorizedInstitution() {
        return adminEntity.getAdminAuth().isInstitution();
    }

    public boolean hasAuthorizedAdvanced() {
        return adminEntity.getAdminAuth().isAdvanced();
    }

    public boolean hasAuthorizedAdmin() {
        return adminEntity.getAdminAuth().isAdmin();
    }

    public boolean hasAuthorizedArticle() {
        return adminEntity.getAdminAuth().isArticle();
    }

    public boolean hasAuthorizedPicture() {
        return adminEntity.getAdminAuth().isPicture();
    }

    public boolean hasAuthorizedResource() {
        return adminEntity.getAdminAuth().isResource();
    }

    public boolean hasAuthorizedBanner() {
        return adminEntity.getAdminAuth().banner();
    }

    public Long getId() {
        return adminEntity.getId();
    }

}
