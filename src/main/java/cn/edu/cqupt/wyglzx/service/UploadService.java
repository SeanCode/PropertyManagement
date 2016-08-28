package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Config;
import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.AttachmentDao;
import cn.edu.cqupt.wyglzx.dao.PhotoDao;
import cn.edu.cqupt.wyglzx.entity.AttachmentEntity;
import cn.edu.cqupt.wyglzx.entity.PhotoEntity;
import cn.edu.cqupt.wyglzx.exception.UploadFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by cc on 16/8/27.
 */
@Component
public class UploadService {

    @Autowired
    AttachmentDao attachmentDao;

    @Autowired
    PhotoDao photoDao;

    public AttachmentEntity saveAttchment(String name, String url) {

        AttachmentEntity attachmentEntity = new AttachmentEntity();
        attachmentEntity.setName(name);
        attachmentEntity.setUrl(url);
        attachmentEntity.setCreateTime(Util.time());
        attachmentEntity.setUpdateTime(attachmentEntity.getCreateTime());

        return attachmentDao.save(attachmentEntity);
    }

    public String uploadPhoto(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                //以时间戳命名图片
                String name = file.getOriginalFilename();
                int position = name.lastIndexOf(".");
                String suffix = name.substring(position > 0 ? position : 0, name.length());
                String imgName = Util.time() + suffix;
                Path path = Paths.get(Config.getImgPath(), imgName);
                Files.copy(file.getInputStream(), path);

                return imgName;
            } catch (IOException | RuntimeException e) {
                throw new UploadFailedException();
            }
        } else {
            throw new UploadFailedException("上传文件为空");
        }
    }

}
