package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.dao.OrgDao;
import cn.edu.cqupt.wyglzx.entity.OrgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by cc on 16/7/31.
 */
@Component
public class OrgService {

    @Autowired
    OrgDao orgDao;

    public List<OrgEntity> getIntroList() {

        return orgDao.getIntroList();
    }

}
