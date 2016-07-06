package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.TestService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/7/6.
 */
@RestController
@RequestMapping("/api/private/v1/test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/admin")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getAdmin() {

        return new DataResponse().put("admin", testService.getAdmin());
    }
}
