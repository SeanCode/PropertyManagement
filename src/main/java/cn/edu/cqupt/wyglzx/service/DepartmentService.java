package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.DepartmentDao;
import cn.edu.cqupt.wyglzx.entity.DepartmentEntity;
import cn.edu.cqupt.wyglzx.exception.ExistsException;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by cc on 16/6/25.
 */
@Component
public class DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    UserService userService;

    public DepartmentEntity checkDepartmentById(Long id) {
        DepartmentEntity department = departmentDao.getDepartmentById(id);
        if (department == null) {
            throw new NotExistsException();
        }
        return department;
    }

    public void checkNotExistsByName(Long parentId, String name) {
        DepartmentEntity departmentEntity = departmentDao.getDepartmentByParentIdAndName(parentId, name);
        if (departmentEntity != null) {
            throw new ExistsException();
        }
    }

    public List<DepartmentEntity> getDepartmentListByRootId(Long rootId) {
        return departmentDao.getDepartmentListByRootId(rootId);
    }

    public DepartmentEntity getDepartmentTree(Long id, int level, boolean withUserList) {

        DepartmentEntity parent = checkDepartmentById(id);

        List<DepartmentEntity> list = departmentDao.getDepartmentListByRootId(parent.getRootId());

        int maxLevel = level <= 0 ? 0 : parent.getLevel() + level;

        if (withUserList) {
            parent.setUserList(userService.getUserListByDepartmentId(parent.getId()));
        }

        parent.setChildren(getDepartmentChildrenTreeByParent(parent, maxLevel, list, withUserList));

        return parent;
    }

    public List<DepartmentEntity> getDepartmentChildrenTreeByParent(DepartmentEntity parent, int maxLevel, List<DepartmentEntity> departments, boolean withUserList) {
        List<DepartmentEntity> children = getChildrenByParentAndDepartments(parent, departments);

        for (DepartmentEntity department : children) {
            if (maxLevel == 0 || department.getLevel() < maxLevel) {

                if (withUserList) {
                    department.setUserList(userService.getUserListByDepartmentId(department.getId()));
                }
                department.setChildren(getDepartmentChildrenTreeByParent(department, maxLevel, departments, withUserList));
            }
        }
        return children;
    }

    public List<DepartmentEntity> getChildrenByParentAndDepartments(DepartmentEntity parent, List<DepartmentEntity> departments) {
        List<DepartmentEntity> children = new ArrayList<>();
        for (DepartmentEntity department : departments) {
            if (Objects.equals(department.getParentId(), parent.getId())) {
                children.add(department);
            }
        }
        return children;
    }

    public DepartmentEntity updateDepartmentName(Long id, String newName) {

        DepartmentEntity department = departmentDao.getDepartmentById(id);

        checkNotExistsByName(department.getParentId(), newName);

        department.setName(newName);
        department.setUpdateTime(Util.time());
        department = departmentDao.save(department);

        return department;
    }

    public DepartmentEntity getDepartmentWithUserList(Long id) {
        DepartmentEntity department = checkDepartmentById(id);

        department.setUserList(userService.getUserListByDepartmentId(id));

        return department;
    }

    public DepartmentEntity addDepartment(Long parentId, String name) {

        DepartmentEntity parent = checkDepartmentById(parentId);

        checkNotExistsByName(parent.getId(), name);

        DepartmentEntity department = new DepartmentEntity();
        department.setName(name);
        department.setParentId(parent.getId());
        department.setRootId(parent.getRootId());
        department.setCreateTime(Util.time());
        department.setUpdateTime(department.getCreateTime());
        department.setLevel(parent.getLevel() + 1);
        department = departmentDao.save(department);

        return department;
    }

    public void removeDepartment(Long id) {
        DepartmentEntity department = checkDepartmentById(id);

        department.setWeight(-1);
        department.setUpdateTime(Util.time());

        departmentDao.save(department);
    }
}
