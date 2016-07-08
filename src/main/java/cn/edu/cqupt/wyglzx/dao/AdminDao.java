package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/5/28.
 */
@Repository
public interface AdminDao extends JpaRepository<AdminEntity, Long> {


    @Query(value = "select * from admin where weight >= 0", nativeQuery = true)
    List<AdminEntity> list();

    @Query(value = "select * from admin where name = :name and weight >= 0 limit 0, 1", nativeQuery = true)
    AdminEntity existByName(@Param("name") String name);

    @Query(value = "select * from admin where id = :id and weight >= 0", nativeQuery = true)
    AdminEntity existsById(@Param("id") Long id);

}
