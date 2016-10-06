package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/6/25.
 */
@Repository
public interface InstitutionDao extends JpaRepository<InstitutionEntity, Long> {

    @Query(value = "select * from institution where id = :id and weight >= 0", nativeQuery = true)
    InstitutionEntity getInstitutionById(@Param("id") Long id);

    @Query(value = "select * from institution where name = :name and weight >= 0 limit 0,1", nativeQuery = true)
    InstitutionEntity getInstitutionByName(@Param("name") String name);

    List<InstitutionEntity> findByWeight(Integer weight);

    @Query(value = "select count(*) from institution where weight >= 0", nativeQuery = true)
    Integer getInstitutionCount();

}
