package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cc on 16/7/6.
 */
@Component
public class TestService {

    @Autowired
    AuthenticationFacadeService authenticationFacadeService;

    public AdminEntity getAdmin() {

        return authenticationFacadeService.getAuthentication().getAdmin();
    }

}
