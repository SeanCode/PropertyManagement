package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.ConfigService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/6/24.
 */
@RestController("Private.ConfigController")
@RequestMapping("/api/private/v1/config")
@Component
public class ConfigController {

    @Autowired
    ConfigService configService;

    @RequestMapping("/price")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getPrice(@RequestParam(name = "year", required = false) Integer year,
                                 @RequestParam(name = "month", required = false) Integer month) {

        return new DataResponse().put("config", configService.getPriceByYearAndMonth(year, month));
    }

    @RequestMapping("/price-default-update")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse updateDefaultPrice(@RequestParam(name = "water", required = false) Double water,
                                           @RequestParam(name = "ele", required = false) Double ele,
                                           @RequestParam(name = "gas", required = false) Double gas) {

        return new DataResponse().put("config", configService.updateDefaultPrice(water, ele, gas));
    }

    @RequestMapping("/price-specific-save")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse saveSpecificPrice(@RequestParam("year") Integer year,
                                          @RequestParam("month") Integer month,
                                          @RequestParam("water") Double water,
                                          @RequestParam("ele") Double ele,
                                          @RequestParam("gas") Double gas) {

        return new DataResponse().put("config", configService.savePrice(year, month, water, ele, gas));
    }

    @RequestMapping("/banner-list")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getBannerList() {

        return new DataResponse().put("banner_list", configService.getBannerList());
    }

    @RequestMapping("/banner-update")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse updateBanner(@RequestParam("id") Long id, @RequestParam("url") String url) {

        return new DataResponse().put("banner_list", configService.updateBanner(id, url));
    }

    @RequestMapping("/banner-add")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse addBanner(@RequestParam("url")String url) {


        return new DataResponse().put("banner", configService.addBanner(url));
    }

}
