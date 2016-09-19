package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.SuggestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/9/5.
 */
@Repository
public interface SuggestionDao extends JpaRepository<SuggestionEntity, Long> {

    @Query(value = "select * from suggestion where weight >= 0 order by create_time DESC limit :offset,:num", nativeQuery = true)
    List<SuggestionEntity> getSuggestionList(@Param("offset") Integer offset, @Param("num") int limit);

    @Query(value = "SELECT * from suggestion where type = :type and weight >= 0 order by create_time DESC limit :offset,:num", nativeQuery = true)
    List<SuggestionEntity> getSuggestionListByType(@Param("type") Integer type, @Param("offset") Integer offset, @Param("num") int limit);

    @Query(value = "select * from suggestion where status = :status and weight >= 0 order by create_time DESC limit :offset,10", nativeQuery = true)
    List<SuggestionEntity> getSuggestionListByStatus(@Param("status") int status, @Param("offset") Integer offset);

    @Query(value = "select count(*) from suggestion where weight >= 0", nativeQuery = true)
    Integer getCount();

    @Query(value = "select count(*) from suggestion where type = :type and weight >= 0", nativeQuery = true)
    Integer getCountByType(@Param("type") Integer type);

    @Query(value = "select count(*) from suggestion where status = :status and weight >= 0", nativeQuery = true)
    Integer getCountByStatus(@Param("status") int status);

    @Query(value = "select * from suggestion where id = :id and weight >= 0", nativeQuery = true)
    SuggestionEntity getById(@Param("id") Long id);

}
