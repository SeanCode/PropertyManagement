package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.PriceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by cc on 16/6/24.
 */
@Repository
public interface PriceDao extends CrudRepository<PriceEntity, Long> {

    @Query(name = "select * from price where year = :year and month = :month limit 0,1", nativeQuery = true)
    PriceEntity getPriceByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);

}
