package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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

    @Query(value = "select * from photo where cover_id = :cover_id and weight >= 0 order by create_time DESC, id DESC limit :offset,10", nativeQuery = true)
    List<PhotoEntity> getPhotoListByCover(@Param("cover_id") Long coverId, @Param("offset") Integer offset);

}
