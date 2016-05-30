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

    @Autowired
    AdminService adminService;

    @RequestMapping("/")
    public String defaultRoute(HttpServletRequest request, Model model) {
        return index(model);
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model,
                        @RequestParam Optional<String> error) throws InvalidKeySpecException, NoSuchAlgorithmException {

        if (error.isPresent()) {
            model.addAttribute("error", error);
        }
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        return "login";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        return "profile";
    }

    @RequestMapping("/people-manage")
    public String managePeople(Model model) {
        return "people-manage";
    }

    @RequestMapping("/company-manage")
    public String manageCompany(Model model) {
        return "company-manage";
    }
}
