package cn.edu.cqupt.wyglzx.controller;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/7/30.
 */
@RestController
public class DefaultController {

    @RequestMapping("/")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse index() {
        return new DataResponse().put("hello", "world");
    }

}
