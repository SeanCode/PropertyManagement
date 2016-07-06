package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.model.AdminDetail;
import cn.edu.cqupt.wyglzx.service.interfaces.IAuthenticationFacade;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by cc on 16/7/6.
 */
@Component
public class AuthenticationFacadeService implements IAuthenticationFacade{

    @Override
    public AdminDetail getAuthentication() {
        return (AdminDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
