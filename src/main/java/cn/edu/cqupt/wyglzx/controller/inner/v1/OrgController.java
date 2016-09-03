package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.OrgService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/7/31.
 */
@RestController("Private.OrgController")
@RequestMapping("/api/private/v1/org")
@Component
public class OrgController {

    @Autowired
    OrgService orgService;


    @RequestMapping("/intro-list")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getIntroductionList() {

        return new DataResponse().put("intro_list", orgService.getIntroList());
    }

    @RequestMapping("/intro-update")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse updateIntroduction(@RequestParam("id") Long id, @RequestParam("content") String content) {

        return new DataResponse().put("intro_list", orgService.updateIntro(id, content));
    }

}
