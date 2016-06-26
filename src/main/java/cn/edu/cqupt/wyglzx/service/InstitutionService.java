package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.InstitutionDao;
import cn.edu.cqupt.wyglzx.entity.InstitutionEntity;
import cn.edu.cqupt.wyglzx.exception.ExistsException;
import cn.edu.cqupt.wyglzx.exception.InvalidParamsException;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by cc on 16/6/26.
 */
@Component
public class InstitutionService {

    @Autowired
    InstitutionDao institutionDao;

    public InstitutionEntity checkInstitutionById(Long id) {
        InstitutionEntity institution = institutionDao.getInstitutionById(id);
        if (institution == null) {
            throw new NotExistsException();
        }
        return institution;
    }

    public void checkNotExistsByName(String name) {
        InstitutionEntity institutionEntity = institutionDao.getInstitutionByName(name);
        if (institutionEntity != null) {
            throw new ExistsException();
        }
    }

    public List<InstitutionEntity> getInstitutionList() {

        return institutionDao.findByWeight(0);
    }

    public InstitutionEntity updateInfo(Long id, String name, String people, String contact, String description, String remark, String code) {
        InstitutionEntity institution = checkInstitutionById(id);

        checkNotExistsByName(name);

        if (StringUtils.isNotBlank(name)) {
            institution.setName(name);
        }
        if (StringUtils.isNotBlank(people)) {
            institution.setPeople(people);
        }
        if (StringUtils.isNotBlank(contact)) {
            institution.setContact(contact);
        }
        if (StringUtils.isNotBlank(description)) {
            institution.setDescription(description);
        }
        if (StringUtils.isNotBlank(remark)) {
            institution.setRemark(remark);
        }
        if (StringUtils.isNotBlank(code)) {
            institution.setCode(code);
        }
        institution.setUpdateTime(Util.time());

        institution = institutionDao.save(institution);

        return institution;
    }

    public InstitutionEntity add(String name, String people, String contact, String description, String remark, String code) {
        checkNotExistsByName(name);
        InstitutionEntity institution = new InstitutionEntity();
        if (!StringUtils.isNotBlank(name)) {
            throw new InvalidParamsException("name");
        }
        institution.setName(name);
        if (StringUtils.isNotBlank(people)) {
            institution.setPeople(people);
        }
        if (StringUtils.isNotBlank(contact)) {
            institution.setContact(contact);
        }
        if (StringUtils.isNotBlank(description)) {
            institution.setDescription(description);
        }
        if (StringUtils.isNotBlank(remark)) {
            institution.setRemark(remark);
        }
        if (StringUtils.isNotBlank(code)) {
            institution.setCode(code);
        }
        institution.setWeight(0);
        institution.setCreateTime(Util.time());
        institution.setUpdateTime(institution.getCreateTime());

        institution = institutionDao.save(institution);

        return institution;
    }

    public void delete(Long id) {
        InstitutionEntity institutionEntity = checkInstitutionById(id);
        institutionEntity.setWeight(-1);
        institutionEntity.setUpdateTime(Util.time());

        institutionDao.save(institutionEntity);
    }
}
