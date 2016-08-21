package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.CoverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/7/31.
 */
@Repository
public interface CoverDao extends JpaRepository<CoverEntity, Long> {

    @Query(value = "select * from cover where type = :type and weight >= 0", nativeQuery = true)
    List<CoverEntity> getCoverListByType(@Param("type") Integer type);

    @Query(value = "select * from cover where id = :id and weight >= 0", nativeQuery = true)
    CoverEntity getCoverById(@Param("id") Long id);
}
