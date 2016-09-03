package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/6/24.
 */
@Repository
public interface ConfigDao extends JpaRepository<ConfigEntity, Long> {

    @Query(value = "select * from config where type = :type limit 0,1", nativeQuery = true)
    ConfigEntity getConfigByType(@Param("type") Integer type);

    @Query(value = "select * from config where type = 20000 limit 4", nativeQuery = true)
    List<ConfigEntity> getBannerList();

    @Query(value = "select * from config where id = :id and type = 20000 limit 0,1", nativeQuery = true)
    ConfigEntity getBannerById(@Param("id") Long id);

}
