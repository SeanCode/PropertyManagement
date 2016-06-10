package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.dao.AdminDao;
import cn.edu.cqupt.wyglzx.model.AdminDetail;
import cn.edu.cqupt.wyglzx.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by cc on 16/5/29.
 */
@Component
public class AdminDetailService implements UserDetailsService {

    @Autowired
    AdminDao adminDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminEntity admin = adminDao.existByName(username);
        if (admin == null) {
            throw new UsernameNotFoundException("not found");
        }
        return new AdminDetail(admin);
    }
}
