package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.OrgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/7/31.
 */
@Repository
public interface OrgDao extends JpaRepository<OrgEntity, Long> {

    @Query(value = "select * from org where weight >= 0", nativeQuery = true)
    List<OrgEntity> getIntroList();

    @Query(value = "select * from org where id = :id and weight >= 0", nativeQuery = true)
    OrgEntity getOrgById(@Param("id") Long id);

}
