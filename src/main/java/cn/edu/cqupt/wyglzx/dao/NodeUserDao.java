package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.NodeUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cc on 16/6/25.
 */
@Repository
public interface NodeUserDao extends CrudRepository<NodeUserEntity, Long> {
}
