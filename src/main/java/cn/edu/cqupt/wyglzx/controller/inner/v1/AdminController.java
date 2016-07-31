package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.AdminService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by cc on 16/5/29.
 */
@RestController("Private.AdminController")
@RequestMapping("/api/private/v1/admin")
@Component
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse updatePassword(@RequestParam("name") String name,
                                       @RequestParam("password") String password) throws InvalidKeySpecException, NoSuchAlgorithmException {

        return new DataResponse().put("admin", adminService.login(name, password));
    }

    @RequestMapping("/info")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getAdminInfo(@RequestParam("id") Long id) {
        return new DataResponse().put("admin", adminService.getAdminInfo(id));
    }

    @RequestMapping("/add")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse addAdmin(@RequestParam("name") String name,
                                 @RequestParam(value = "username", required = false, defaultValue = "") String username,
                                 @RequestParam(value = "privilege", required = false, defaultValue = "0") Integer privilege) throws InvalidKeySpecException, NoSuchAlgorithmException {

        return new DataResponse().put("admin", adminService.addAdmin(name, username, privilege));
    }

    @RequestMapping("/status-update")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse updateAdminStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status) {

        adminService.updateAdminStatus(id, status);
        return new DataResponse();
    }

    @RequestMapping("/delete")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse deleteAdmin(@RequestParam("id") Long id) {

        adminService.deleteAdmin(id);
        return new DataResponse();
    }

    @RequestMapping("/password-update")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse updatePassword(@RequestParam("name") String name,
                                       @RequestParam("password") String password,
                                       @RequestParam("old_password") String oldPassword) throws InvalidKeySpecException, NoSuchAlgorithmException {


        return new DataResponse().put("admin", adminService.updatePassword(name, password, oldPassword));
    }

    @RequestMapping("/password-reset")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse resetPassword(@RequestParam("id") Long id) throws InvalidKeySpecException, NoSuchAlgorithmException {

        adminService.resetPassword(id);

        return new DataResponse();
    }

    @RequestMapping("/info-update-by-root-user")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse updateInfoByRoot(@RequestParam("name") String name,
                                         @RequestParam(value = "new_username", required = false) String newUserName,
                                         @RequestParam(value = "new_name", required = false) String newName,
                                         @RequestParam(value = "new_privilege") int newPrivilege) throws InvalidKeySpecException, NoSuchAlgorithmException {

        return new DataResponse().put("admin", adminService.updateAdminByRoot(name, newUserName, newName, newPrivilege));
    }

    @RequestMapping("/privilege-update")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse updateAdminPrivilege(@RequestParam("id") Long id,
                                             @RequestParam("index") Integer index) {

        adminService.updateAdminPrivilege(id, index);
        return new DataResponse();
    }

    @RequestMapping("/info-update")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse updateInfo(@RequestParam("name") String name,
                                   @RequestParam("password") String password,
                                   @RequestParam(value = "new_username", required = false) String newUserName,
                                   @RequestParam(value = "new_name", required = false) String newName) throws InvalidKeySpecException, NoSuchAlgorithmException {

        return new DataResponse().put("admin", adminService.updateAdmin(name, password, newUserName, newName));
    }

    @RequestMapping("/list")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getList() {
        return new DataResponse().put("admin_list", adminService.getList());
    }
}
