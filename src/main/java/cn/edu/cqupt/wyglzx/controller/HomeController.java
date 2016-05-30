package cn.edu.cqupt.wyglzx.controller;

import cn.edu.cqupt.wyglzx.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

/**
 * Created by cc on 16/5/29.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String defaultRoute(HttpServletRequest request, Model model) {
        return "index";
    }

}
