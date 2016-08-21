package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/8/21.
 */
@Repository
public interface AttachmentDao extends JpaRepository<AttachmentEntity, Long> {

    @Query(value = "select * from attachment where id in (:ids) and weight >= 0", nativeQuery = true)
    List<AttachmentEntity> getAttachments(@Param("ids") String ids);

}
