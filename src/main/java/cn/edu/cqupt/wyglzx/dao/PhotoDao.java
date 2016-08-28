package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/7/31.
 */
@Repository
public interface PhotoDao extends JpaRepository<PhotoEntity, Long> {

    @Query(value = "select * from photo where weight >= 0 order by create_time DESC limit 15", nativeQuery = true)
    List<PhotoEntity> getLatestPhotos();

    @Query(value = "select * from photo where cover_id = :cover_id and weight >= 0 order by create_time DESC, id DESC limit :offset,9", nativeQuery = true)
    List<PhotoEntity> getPhotoListByCover(@Param("cover_id") Long coverId, @Param("offset") Integer offset);

    @Query(value = "select count(*) from photo where cover_id = :cover_id and weight >= 0", nativeQuery = true)
    Integer getPhotoAmount(@Param("cover_id") Long coverId);

    @Query(value = "select * from photo where id = :id and weight >= 0", nativeQuery = true)
    PhotoEntity getPhotoById(@Param("id")Long id);

    @Modifying
    @Query(value = "update photo set weight = -1 where cover_id = :cover_id and weight >= 0", nativeQuery = true)
    void deletePhotosInBatch(@Param("cover_id")Long coverId);

}
