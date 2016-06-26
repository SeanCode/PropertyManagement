package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.DepartmentService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/6/25.
 */
@RestController
@RequestMapping("/api/private/v1/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/tree")
    @JsonView(OutputEntityJsonView.Tree.class)
    public DataResponse getDepartmentTree(@RequestParam("id") Long id,
                                          @RequestParam(value = "level", required = false) Integer level) {

        if (level == null || level < 0) {
            level = 0;
        }

        return new DataResponse().put("department_tree", departmentService.getDepartmentTree(id, level, false));
    }

    @RequestMapping("/tree-list")
    @JsonView(OutputEntityJsonView.Tree.class)
    public DataResponse getDepartmentTreeList(@RequestParam("root_id") Long rootId) {
        return new DataResponse().put("department_list", departmentService.getDepartmentListByRootId(rootId));
    }

    @RequestMapping("/list")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getDepartmentList(@RequestParam("root_id") Long rootId) {
        return new DataResponse().put("department_list", departmentService.getDepartmentListByRootId(rootId));
    }

    @RequestMapping("/tree-with-user-list")
    @JsonView(OutputEntityJsonView.Tree.class)
    public DataResponse getDepartmentTreeWithUserList(@RequestParam("id") Long id,
                                                      @RequestParam(value = "level", required = false) Integer level) {

        if (level == null || level < 0) {
            level = 0;
        }

        return new DataResponse().put("department_tree", departmentService.getDepartmentTree(id, level, true));
    }

    @RequestMapping("/with-user-list")
    @JsonView(OutputEntityJsonView.Tree.class)
    public DataResponse getDepartmentUserList(@RequestParam("id") Long id) {
        return new DataResponse().put("department", departmentService.getDepartmentWithUserList(id));
    }

    @RequestMapping("/name-update")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse updateDepartmentName(@RequestParam("id") Long id,
                                             @RequestParam("name") String newName) {

        return new DataResponse().put("department", departmentService.updateDepartmentName(id, newName));
    }

    @RequestMapping("/add")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse addDepartment(@RequestParam("parent_id") Long parentId,
                                      @RequestParam("name") String name) {

        return new DataResponse().put("department", departmentService.addDepartment(parentId, name));
    }

    @RequestMapping("/remove")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse removeDepartment(@RequestParam("id") Long id) {

        departmentService.removeDepartment(id);
        return new DataResponse();
    }


}
