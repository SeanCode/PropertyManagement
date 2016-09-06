package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.CoverDao;
import cn.edu.cqupt.wyglzx.dao.PhotoDao;
import cn.edu.cqupt.wyglzx.entity.CoverEntity;
import cn.edu.cqupt.wyglzx.entity.PhotoEntity;
import cn.edu.cqupt.wyglzx.exception.NotAllowedException;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cc on 16/7/31.
 */
@Transactional
@Component
public class PhotoService {

    @Autowired
    CoverDao coverDao;

    @Autowired
    PhotoDao photoDao;

    @Autowired
    AuthenticationFacadeService authenticationFacadeService;

    public List<CoverEntity> getCoverList(Integer type, Integer page, Integer limit) {
        if (page < 1) {
            page = 1;
        }
        if (limit < 4) {
            limit = 4;
        }
        if (type == 0) {
            return coverDao.getCoverList((page - 1) * limit, limit);
        }
        return coverDao.getCoverListByType(type, (page - 1) * limit, limit);
    }

    public Integer getCoverAmount(Integer type) {
        if (type == 0) {
            return coverDao.getCoverAmount();
        }
        return coverDao.getCoverAmountByType(type);
    }

    public List<PhotoEntity> getLatestPhotos() {
        List<PhotoEntity> photos = photoDao.getLatestPhotos();
        for (PhotoEntity photo : photos) {
            photo.setCover(coverDao.getCoverById(photo.getCoverId()));
        }
        return photos;
    }

    public List<CoverEntity> getLatestCovers() {
        return coverDao.getCoverLatest();
    }

    public List<PhotoEntity> getPhotos(Long coverId, Integer page) {
        if (page <= 1) {
            page = 1;
        }
        coverDao.updateReadTimes(coverId);
        return photoDao.getPhotoListByCover(coverId, (page - 1) * 9);
    }

    public CoverEntity getCover(Long coverId) {

        return coverDao.getCoverById(coverId);
    }

    public Integer getPhotoAmount(Long coverId) {

        return photoDao.getPhotoAmount(coverId);
    }

    public void deletePhoto(Long photoId) {
        if (!authenticationFacadeService.getAuthentication().hasAuthorizedPicture()) {
            throw new NotAllowedException("尚未授权!请联系管理员!");
        }
        PhotoEntity photoEntity = photoDao.getPhotoById(photoId);
        if (photoEntity == null) {
            throw new NotExistsException();
        }
        photoEntity.setWeight(-1);
        photoEntity.setUpdateTime(Util.time());
        photoDao.save(photoEntity);
    }

    @Transactional
    public void deleteCover(Long coverId) {
        if (!authenticationFacadeService.getAuthentication().hasAuthorizedPicture()) {
            throw new NotAllowedException("尚未授权!请联系管理员!");
        }
        CoverEntity coverEntity = coverDao.getCoverById(coverId);
        if (coverEntity == null) {
            throw new NotExistsException();
        }
        photoDao.deletePhotosInBatch(coverId);
        coverEntity.setWeight(-1);
        coverEntity.setUpdateTime(Util.time());
        coverDao.save(coverEntity);
    }

    public void addPhoto(Long coverId, String url) {
        if (!authenticationFacadeService.getAuthentication().hasAuthorizedPicture()) {
            throw new NotAllowedException("尚未授权!请联系管理员!");
        }
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setUrl(url);
        photoEntity.setCoverId(coverId);
        photoEntity.setCreateTime(Util.time());
        photoEntity.setUpdateTime(photoEntity.getCreateTime());
        photoDao.save(photoEntity);
    }

    public void addCover(String name, String url, Integer type) {
        if (!authenticationFacadeService.getAuthentication().hasAuthorizedPicture()) {
            throw new NotAllowedException("尚未授权!请联系管理员!");
        }
        CoverEntity coverEntity = new CoverEntity();
        coverEntity.setAdminId(authenticationFacadeService.getAuthentication().getId());
        coverEntity.setName(name);
        coverEntity.setCoverUrl(url);
        coverEntity.setType(type);
        coverEntity.setCreateTime(Util.time());
        coverEntity.setUpdateTime(coverEntity.getCreateTime());
        coverDao.save(coverEntity);
    }

    public void updateCover(Long id, String name, String url) {
        if (!authenticationFacadeService.getAuthentication().hasAuthorizedPicture()) {
            throw new NotAllowedException("尚未授权!请联系管理员!");
        }
        CoverEntity coverEntity = coverDao.getCoverById(id);
        if (coverEntity == null) {
            throw new NotExistsException();
        }
        boolean flag = false;
        if (StringUtils.isNotBlank(name)) {
            coverEntity.setName(name);
            flag = true;
        }
        if (StringUtils.isNotBlank(url)) {
            coverEntity.setCoverUrl(url);
            flag = true;
        }
        if (flag) {
            coverEntity.setUpdateTime(Util.time());
            coverDao.save(coverEntity);
        }
    }

}
