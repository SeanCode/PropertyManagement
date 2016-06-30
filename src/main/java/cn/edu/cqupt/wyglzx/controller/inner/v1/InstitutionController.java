package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.InstitutionService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/6/26.
 */
@RestController
@RequestMapping("/api/private/v1/institution")
public class InstitutionController {

    @Autowired
    InstitutionService institutionService;

    @RequestMapping("/list")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getList() {

        return new DataResponse().put("institution_list", institutionService.getInstitutionList());
    }

    @RequestMapping("/info-update")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse updateInfo(@RequestParam("id") Long id,
                                   @RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "people", required = false) String people,
                                   @RequestParam(value = "contact", required = false) String contact,
                                   @RequestParam(value = "description", required = false) String description,
                                   @RequestParam(value = "remark", required = false) String remark,
                                   @RequestParam(value = "code", required = false) String code) {

        return new DataResponse().put("institution", institutionService.updateInfo(id, name, people, contact, description, remark, code));
    }

    @RequestMapping("/add")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse add(@RequestParam("name") String name,
                            @RequestParam(value = "people", required = false) String people,
                            @RequestParam(value = "contact", required = false) String contact,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "remark", required = false) String remark,
                            @RequestParam(value = "code", required = false) String code) {

        return new DataResponse().put("institution", institutionService.add(name, people, contact, description, remark, code));
    }

    @RequestMapping("/delete")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse delete(@RequestParam("id") Long id) {

        institutionService.delete(id);
        return new DataResponse();
    }

    @RequestMapping("/detail")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getDetail(@RequestParam("id") Long id) {

        return new DataResponse().put("institution", institutionService.getDetail(id));
    }

}
