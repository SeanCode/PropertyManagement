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

/**
 * Created by cc on 16/5/29.
 */
@Controller
public class HomeController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/")
    public String defaultRoute(Model model) {
        return "login";
//        return index(model);
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model, @RequestParam(value = "username", required = false) String userName, @RequestParam(value = "password", required = false) String password) throws InvalidKeySpecException, NoSuchAlgorithmException {

        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return "login";
        }

        if (adminService.login(userName, password)) {
            System.out.println("登陆成功");
            return "redirect:/index";
        } else {
            model.addAttribute("success", false);
        }

        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }

}
