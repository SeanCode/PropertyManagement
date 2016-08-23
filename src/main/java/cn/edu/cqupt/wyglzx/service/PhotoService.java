package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.dao.CoverDao;
import cn.edu.cqupt.wyglzx.dao.PhotoDao;
import cn.edu.cqupt.wyglzx.entity.CoverEntity;
import cn.edu.cqupt.wyglzx.entity.PhotoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by cc on 16/7/31.
 */
@Component
public class PhotoService {

    @Autowired
    CoverDao coverDao;

    @Autowired
    PhotoDao photoDao;

    public List<CoverEntity> getCoverList(Integer type, Integer page) {
        if (page < 1) {
            page = 1;
        }
        return coverDao.getCoverListByType(type, (page - 1) * 10);
    }

    public Integer getCoverAmount(Integer type) {

        return coverDao.getCoverAmount(type);
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
        return photoDao.getPhotoListByCover(coverId, (page - 1) * 10);
    }

    public Integer getPhotoAmount(Long coverId) {

        return photoDao.getPhotoAmount(coverId);
    }

}
