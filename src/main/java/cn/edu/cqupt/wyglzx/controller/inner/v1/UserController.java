package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/6/25.
 */
@RestController("Private.UserController")
@RequestMapping("/api/private/v1/user")
@Component
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/list-by-department")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getUserListByDepartment(@RequestParam("department_id") Long departmentId) {

        return new DataResponse().put("user_list", userService.getUserListByDepartmentId(departmentId));
    }

    @RequestMapping("/detail")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getUserDetail(@RequestParam("id") Long id) {
        return new DataResponse().put("user", userService.getUserById(id));
    }

    @RequestMapping("/info-update")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse updateUserInfo(@RequestParam("id") Long id,
                                       @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                       @RequestParam(value = "username", required = false, defaultValue = "") String username,
                                       @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                                       @RequestParam(value = "id_card", required = false, defaultValue = "") String idCard,
                                       @RequestParam(value = "school_card", required = false, defaultValue = "") String schoolCard,
                                       @RequestParam(value = "remark", required = false, defaultValue = "") String remark) {

        return new DataResponse().put("user", userService.updateUserInfo(id, name, username, phone, idCard, schoolCard, remark));
    }

    @RequestMapping("/delete")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return new DataResponse();
    }

    @RequestMapping("/add")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse addUser(@RequestParam(value = "name") String name,
                                @RequestParam(value = "department_id") Long departmentId,
                                @RequestParam(value = "username", required = false, defaultValue = "") String username,
                                @RequestParam(value = "phone", required = false, defaultValue = "") String phone,
                                @RequestParam(value = "id_card") String idCard,
                                @RequestParam(value = "school_card", required = false, defaultValue = "") String schoolCard,
                                @RequestParam(value = "remark", required = false, defaultValue = "") String remark) {

        return new DataResponse().put("user", userService.addUser(name, departmentId, username, phone, idCard, schoolCard, remark));
    }

    @RequestMapping("/amount")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getUserAmount() {
        return new DataResponse().put("user_amount", userService.getUserAmount());
    }

}
