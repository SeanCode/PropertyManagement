package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.RecordEntity;
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
public interface RecordDao extends JpaRepository<RecordEntity, Long> {

    @Query(value = "select * from record where id = :id and weight >= 0", nativeQuery = true)
    RecordEntity getRecordById(@Param("id") Long id);

    @Query(value = "select * from record where meter_id = :meter_id and time = :time and type = 2 and weight >= 0 limit 0,1", nativeQuery = true)
    RecordEntity getByMeterIdAndTime(@Param("meter_id") Long meterId, @Param("time") Long time);

    @Query(value = "select * from record where node_id = :node_id and year = :year and month = :month and type = 2 and weight >= 0", nativeQuery = true)
    List<RecordEntity> getRecordTempListByNodeAndYearMonth(@Param("node_id") Long nodeId, @Param("year") Integer year, @Param("month") Integer month);

    @Query(value = "select * from record where meter_id = :meter_id and year = :year and month = :month and type = 1 and weight >= 0 limit 0,1", nativeQuery = true)
    RecordEntity getMeterYearMonthRecord(@Param("meter_id") Long meterId, @Param("year") Integer year, @Param("month") Integer month);

}
