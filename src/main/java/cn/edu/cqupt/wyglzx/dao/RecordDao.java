package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cc on 16/6/24.
 */
@Repository
public interface RecordDao extends JpaRepository<RecordEntity, Long> {

}
