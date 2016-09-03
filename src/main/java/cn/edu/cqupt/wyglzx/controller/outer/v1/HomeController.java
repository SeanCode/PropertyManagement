package cn.edu.cqupt.wyglzx.controller.outer.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.ConfigService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/8/31.
 */
@RestController("Public.HomeController")
@RequestMapping("/api/public/v1/home")
@Component
public class HomeController {

    @Autowired
    ConfigService configService;

    @RequestMapping("/banner-list")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getBannerList() {

        return new DataResponse().put("banner_list", configService.getBannerList());
    }

}
