package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.AttachmentDao;
import cn.edu.cqupt.wyglzx.entity.AttachmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cc on 16/8/27.
 */
@Component
public class UploadService {

    @Autowired
    AttachmentDao attachmentDao;

    public AttachmentEntity saveAttchment(String name, String url) {

        AttachmentEntity attachmentEntity = new AttachmentEntity();
        attachmentEntity.setName(name);
        attachmentEntity.setUrl(url);
        attachmentEntity.setCreateTime(Util.time());
        attachmentEntity.setUpdateTime(attachmentEntity.getCreateTime());

        return attachmentDao.save(attachmentEntity);
    }
}
