package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cc on 16/6/24.
 */
@Repository
public interface UserDao extends CrudRepository<UserEntity, Long> {

}
