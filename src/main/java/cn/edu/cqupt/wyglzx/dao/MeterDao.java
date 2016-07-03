package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.MeterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/6/24.
 */
@Repository
public interface MeterDao extends JpaRepository<MeterEntity, Long> {

    @Query(value = "select * from meter where id = :id and weight >= 0 and status >= 0", nativeQuery = true)
    MeterEntity getMeterById(@Param("id") Long id);

    @Query(value = "select * from meter where id = :id and node_id = :node_id and weight >= 0 and status >= 0", nativeQuery = true)
    MeterEntity getMeterByIdAndNodeId(@Param("id") Long id, @Param("node_id") Long nodeId);

    @Query(value = "select * from meter where node_id = :node_id and type = :type and weight >= 0 and status >= 0 limit 0,1", nativeQuery = true)
    MeterEntity getFirstNormalMeterByNodeIdAndType(@Param("node_id") Long nodeId, @Param("type") Integer type);

    @Query(value = "select * from meter where node_id = :node_id and type < 4 and weight >= 0 and status >= 0", nativeQuery = true)
    List<MeterEntity> getMeterNormalListByNodeId(@Param("node_id") Long nodeId);

    @Query(value = "select * from meter where node_id = :node_id and type > 3 and weight >= 0 and status >= 0", nativeQuery = true)
    List<MeterEntity> getMeterCheckListByNodeId(@Param("node_id") Long nodeId);

    @Query(value = "SELECT * FROM meter as t1, meter as t2 WHERE t1.weight >=0  and t1.type < 4 and t1.status >= 0 and t2.type < 4 and t1.parent_id = t2.id and t2.node_id = :node_id and t2.weight >= 0 and t2.status >= 0", nativeQuery = true)
    List<MeterEntity> getMeterChildrenByNodeId(@Param("node_id") Long nodeId);

}
