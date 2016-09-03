package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.OrgDao;
import cn.edu.cqupt.wyglzx.entity.OrgEntity;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
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

    public List<OrgEntity> updateIntro(Long id, String content) {

        OrgEntity orgEntity = orgDao.getOrgById(id);
        if (orgEntity == null) {
            throw new NotExistsException();
        }
        orgEntity.setContent(content);
        orgEntity.setUpdateTime(Util.time());
        orgDao.save(orgEntity);

        return orgDao.getIntroList();
    }
}
