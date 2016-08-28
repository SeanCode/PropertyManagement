package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.CoverEntity;
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
public interface CoverDao extends JpaRepository<CoverEntity, Long> {

    @Query(value = "select * from cover where type = :type and weight >= 0 order by create_time DESC limit :offset,:limit", nativeQuery = true)
    List<CoverEntity> getCoverListByType(@Param("type") Integer type, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Query(value = "select count(*) from cover where type = :type and weight >= 0", nativeQuery = true)
    Integer getCoverAmountByType(@Param("type") Integer type);

    @Query(value = "select count(*) from cover where weight >= 0", nativeQuery = true)
    Integer getCoverAmount();

    @Query(value = "select * from cover where id = :id and weight >= 0", nativeQuery = true)
    CoverEntity getCoverById(@Param("id") Long id);

    @Query(value = "select * from cover where weight >= 0 order by update_time DESC limit 15", nativeQuery = true)
    List<CoverEntity> getCoverLatest();

    @Query(value = "select * from cover where weight >= 0 order by create_time DESC limit :offset,:limit", nativeQuery = true)
    List<CoverEntity> getCoverList(@Param("offset") Integer offset, @Param("limit") Integer limit);

    @Modifying
    @Query(value = "update cover set cover.read = cover.read + 1 where id = :id and weight >= 0", nativeQuery = true)
    void updateReadTimes(@Param("id") Long id);
}
