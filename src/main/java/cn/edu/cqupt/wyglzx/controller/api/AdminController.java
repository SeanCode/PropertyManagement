package cn.edu.cqupt.wyglzx.controller.api;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.entity.AdminEntity;
import cn.edu.cqupt.wyglzx.service.AdminService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by cc on 16/5/29.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/password-update")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse updatePassword(@RequestParam("name") String name,
                                       @RequestParam("password") String password,
                                       @RequestParam("old_password") String oldPassword) throws InvalidKeySpecException, NoSuchAlgorithmException {


        return new DataResponse().put("admin", adminService.updatePassword(name, password, oldPassword));
    }

    @RequestMapping("/password-reset")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse resetPassword(@RequestParam("name") String name,
                                      @RequestParam("password") String password) throws InvalidKeySpecException, NoSuchAlgorithmException {

        adminService.resetPassword(name, password);

        return new DataResponse();
    }

    @RequestMapping("/info-update-by-root-user")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse updateInfoByRoot(@RequestParam("name") String name,
                                         @RequestParam(value = "new_username", required = false) String newUserName,
                                         @RequestParam(value = "new_name", required = false) String newName,
                                         @RequestParam(value = "new_priv") int newPriv) throws InvalidKeySpecException, NoSuchAlgorithmException {
        AdminEntity adminEntity = adminService.updateAdminByRoot(name, newUserName, newName, newPriv);

        return new DataResponse().put("admin", adminEntity);
    }

    @RequestMapping("/info-update")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse updateInfo(@RequestParam("name") String name,
                                   @RequestParam("password") String password,
                                   @RequestParam(value = "new_username", required = false) String newUserName,
                                   @RequestParam(value = "new_name", required = false) String newName,
                                   @RequestParam(value = "new_priv") int newPriv) throws InvalidKeySpecException, NoSuchAlgorithmException {
        AdminEntity adminEntity = adminService.updateAdmin(name, password, newUserName, newName, newPriv);

        return new DataResponse().put("admin", adminEntity);
    }

    @RequestMapping("/list")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getList() {
        return new DataResponse().put("admin_list", adminService.getList());
    }
}
