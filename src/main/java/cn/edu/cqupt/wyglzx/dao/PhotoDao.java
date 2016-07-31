package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cc on 16/7/31.
 */
@Repository
public interface PhotoDao extends JpaRepository<PhotoEntity, Long> {

}
