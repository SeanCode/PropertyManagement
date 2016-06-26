package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/6/25.
 */
@RestController
@RequestMapping("/api/private/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/list-by-department")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getUserListByDepartment(@RequestParam("department_id") Long departmentId) {

        return new DataResponse().put("user_list", userService.getUserListByDepartmentId(departmentId));
    }
}
