package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.ConfigEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by cc on 16/6/24.
 */
@Repository
public interface ConfigDao extends CrudRepository<ConfigEntity, Long> {

    @Query(value = "select * from config where type = :type limit 0,1", nativeQuery = true)
    ConfigEntity getConfigByType(@Param("type") Integer type);

}
