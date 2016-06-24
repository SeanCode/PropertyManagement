package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.ConfigDao;
import cn.edu.cqupt.wyglzx.dao.PriceDao;
import cn.edu.cqupt.wyglzx.entity.ConfigEntity;
import cn.edu.cqupt.wyglzx.entity.PriceEntity;
import cn.edu.cqupt.wyglzx.exception.InvalidParamsException;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import cn.edu.cqupt.wyglzx.model.PriceConfig;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/6/24.
 */
@RestController
@RequestMapping("/api/private/v1/config")
public class ConfigApiController {

    @Autowired
    ConfigDao configDao;
    @Autowired
    PriceDao priceDao;

    @RequestMapping("/price")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getPrice(@RequestParam(name = "year", required = false) Integer year,
                                 @RequestParam(name = "month", required = false) Integer month) {
        //return specified price
        if (year != null && year > 0 && month != null && month > 0) {
            PriceEntity priceEntity = priceDao.getPriceByYearAndMonth(year, month);
            if (priceEntity != null) {
                PriceConfig config = new PriceConfig();
                config.setId(0);
                config.setData("");
                config.setType(ConfigEntity.TYPE_SPECIAL_PRICE);
                config.setCreateTime(priceEntity.getCreateTime());
                config.setUpdateTime(priceEntity.getUpdateTime());

                ConfigEntity.Price price = new ConfigEntity.Price();
                price.water = priceEntity.getWater();
                price.ele = priceEntity.getEle();
                price.gas = priceEntity.getGas();

                config.setPrice(price);

                return new DataResponse().put("config", config);
            }
        }
        //return default price
        ConfigEntity config = configDao.getConfigByType(ConfigEntity.TYPE_DEFAULT_PRICE);
        if (config == null) {
            throw new NotExistsException();
        }


        return new DataResponse().put("config", PriceConfig.cloneFromConfigEntity(config));
    }

    @RequestMapping("/price-default-update")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse updateDefaultPrice(@RequestParam(name = "water", required = false) Double water,
                                           @RequestParam(name = "ele", required = false) Double ele,
                                           @RequestParam(name = "gas", required = false) Double gas) {

        if ((water != null && water > 0) || (ele != null && ele > 0) || (gas != null && gas > 0)) {
            ConfigEntity config = configDao.getConfigByType(ConfigEntity.TYPE_DEFAULT_PRICE);
            if (config == null) {
                config = new ConfigEntity();
                config.setType(ConfigEntity.TYPE_DEFAULT_PRICE);
                config.setCreateTime(Util.time());
                config.setUpdateTime(config.getCreateTime());

                ConfigEntity.Price price = new ConfigEntity.Price();
                if (water != null && water > 0) {
                    price.water = water;
                }
                if (ele != null && ele > 0) {
                    price.ele = ele;
                }
                if (gas != null && gas > 0) {
                    price.gas = gas;
                }
                config.setData(new Gson().toJson(price));
                configDao.save(config);
            } else {
                ConfigEntity.Price price = new Gson().fromJson(config.getData(), ConfigEntity.Price.class);
                if (water != null && water > 0) {
                    price.water = water;
                }
                if (ele != null && ele > 0) {
                    price.ele = ele;
                }
                if (gas != null && gas > 0) {
                    price.gas = gas;
                }
                config.setData(new Gson().toJson(price));
                config.setUpdateTime(Util.time());
                configDao.save(config);
            }

            return new DataResponse().put("config", PriceConfig.cloneFromConfigEntity(config));

        } else {
            throw new InvalidParamsException();
        }

    }

}
