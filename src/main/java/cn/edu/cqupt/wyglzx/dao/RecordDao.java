package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by cc on 16/6/24.
 */
@Repository
public interface RecordDao extends JpaRepository<RecordEntity, Long> {

    @Query(value = "select * from record where id = :id and weight >= 0", nativeQuery = true)
    RecordEntity getRecordById(@Param("id") Long id);

    @Query(value = "select * from record where meter_id = :meter_id and type = 1 and year = :year and month = :month and weight >= 0 limit 0,1", nativeQuery = true)
    RecordEntity getArchivedRecord(@Param("meter_id") Long meterId, @Param("year") Integer year, @Param("month") Integer month);

    @Query(value = "select * from record where meter_id = :meter_id and time = :time and type = 2 and weight >= 0 limit 0,1", nativeQuery = true)
    RecordEntity getByMeterIdAndTime(@Param("meter_id") Long meterId, @Param("time") Long time);

    @Query(value = "select * from record where node_id = :node_id and year = :year and month = :month and type = 2 and weight >= 0", nativeQuery = true)
    List<RecordEntity> getRecordTempListByNodeAndYearMonth(@Param("node_id") Long nodeId, @Param("year") Integer year, @Param("month") Integer month);

    @Query(value = "select * from record where meter_id = :meter_id and year = :year and month = :month and type = 1 and weight >= 0 limit 0,1", nativeQuery = true)
    RecordEntity getMeterYearMonthRecord(@Param("meter_id") Long meterId, @Param("year") Integer year, @Param("month") Integer month);

    @Query(value = "select * from record where type = 2 and weight >= 0 and status = 0 and id >= " +
            "(select id from record where type = 2 and weight >= 0 and status = 0 limit :offset,1) order by create_time DESC limit 10", nativeQuery = true)
    List<RecordEntity> getPendingList(@Param("offset") Integer offset);

    @Query(value = "select count(*) from record where type = 2 and weight >= 0 and status = 0", nativeQuery = true)
    Integer getPendingCount();

    @Query(value = "select * from record where type = 2 and weight >= 0 and status != 0 and id >= " +
            "(select id from record where type = 2 and status != 0 and weight >= 0 limit :offset,1) order by create_time DESC limit 10", nativeQuery = true)
    List<RecordEntity> getAllCheckedList(@Param("offset") Integer offset);

    @Query(value = "select * from record where type = 2 and weight >= 0 and status != 0 and operator_id = :operator_id and id >= " +
            "(select id from record where type = 2 and weight >= 0 and status != 0 and operator_id = :operator_id limit :offset,1) limit 10", nativeQuery = true)
    List<RecordEntity> getCheckedListByAdmin(@Param("operator_id") Long operatorId, @Param("offset") Integer offset);

    // 这个sql 在jpa里面是只能拿到一条数据 所以还是用下面的比较好
    @Deprecated
    @Query(value = "select * from record where id in (:id_list) and type = 2 and status = 0 and weight >= 0", nativeQuery = true)
    List<RecordEntity> getTempRecordListById(@Param("id_list")String idList);

    List<RecordEntity> findByIdIn(Collection<Long> id);

}
