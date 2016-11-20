package cn.edu.cqupt.wyglzx.controller;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.SystemService;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.stuxuhai.jpinyin.PinyinException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/7/30.
 */
@RestController
public class DefaultController {

    @Autowired
    SystemService systemService;

    @RequestMapping("/")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse index() {
        return new DataResponse().put("hello", "world");
    }

    @RequestMapping("/node-code")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse nodeCode() throws PinyinException {
//        systemService.addCodeForNodeAll();
        return new DataResponse();
    }

}
