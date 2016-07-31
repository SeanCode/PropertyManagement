package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
