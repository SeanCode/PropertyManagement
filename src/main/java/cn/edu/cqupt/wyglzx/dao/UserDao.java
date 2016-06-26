package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/6/24.
 */
@Repository
public interface UserDao extends CrudRepository<UserEntity, Long> {

    @Query(name = "select * from user where department_id = :department_id and weight >= 0", nativeQuery = true)
    List<UserEntity> getUserListByDepartmentId(@Param("department_id") Long departmentId);

}
