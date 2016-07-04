package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.ConfigDao;
import cn.edu.cqupt.wyglzx.dao.PriceDao;
import cn.edu.cqupt.wyglzx.entity.ConfigEntity;
import cn.edu.cqupt.wyglzx.entity.PriceEntity;
import cn.edu.cqupt.wyglzx.exception.InvalidParamsException;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import cn.edu.cqupt.wyglzx.model.PriceConfig;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cc on 16/6/25.
 */
@Component
public class ConfigService {

    @Autowired
    ConfigDao configDao;
    @Autowired
    PriceDao priceDao;

    public PriceConfig getPriceByYearAndMonth(Integer year, Integer month) {
        PriceConfig priceConfig = null;
        //return specified price
        if (year != null && year > 0 && month != null && month > 0) {
            PriceEntity priceEntity = priceDao.getPriceByYearAndMonth(year, month);

            if (priceEntity != null) {
                priceConfig = PriceConfig.cloneFromPriceEntity(priceEntity);
            }
        }
        if (priceConfig != null) {
            return priceConfig;
        }

        //return default price
        ConfigEntity config = configDao.getConfigByType(ConfigEntity.TYPE_DEFAULT_PRICE);
        if (config == null) {
            throw new NotExistsException();
        }

        priceConfig = PriceConfig.cloneFromConfigEntity(config);
        return priceConfig;
    }

    public PriceConfig savePrice(Integer year, Integer month, Double water, Double ele, Double gas) {
        if (year != null && year > 0 && month != null && month > 0) {
            PriceEntity priceEntity = priceDao.getPriceByYearAndMonth(year, month);
            if (priceEntity == null) {
                priceEntity = new PriceEntity();
                priceEntity.setYear(year);
                priceEntity.setMonth(month);
                priceEntity.setCreateTime(Util.time());
                priceEntity.setUpdateTime(priceEntity.getUpdateTime());
            }
            boolean flag = false;
            if (water != null && water > 0) {
                priceEntity.setWater(water);
                flag = true;
            }
            if (ele != null && ele > 0) {
                priceEntity.setEle(ele);
                flag = true;
            }
            if (gas != null && gas > 0) {
                priceEntity.setGas(gas);
                flag = true;
            }
            if (flag) {
                priceEntity.setUpdateTime(Util.time());
                priceEntity = priceDao.save(priceEntity);
                return PriceConfig.cloneFromPriceEntity(priceEntity);
            }
        }

        throw new InvalidParamsException();
    }

    public PriceConfig updateDefaultPrice(Double water, Double ele, Double gas) {

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

            return PriceConfig.cloneFromConfigEntity(config);

        } else {
            throw new InvalidParamsException();
        }
    }

}
