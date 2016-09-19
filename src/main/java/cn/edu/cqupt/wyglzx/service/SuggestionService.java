package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.SuggestionDao;
import cn.edu.cqupt.wyglzx.entity.SuggestionEntity;
import cn.edu.cqupt.wyglzx.exception.InvalidParamsException;
import cn.edu.cqupt.wyglzx.exception.NotAllowedException;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cc on 16/9/5.
 */
@Component
@Transactional
public class SuggestionService {

    @Autowired
    SuggestionDao suggestionDao;

    @Autowired
    AuthenticationFacadeService authenticationFacadeService;

    public List<SuggestionEntity> getSuggestionList(int type, int page, int limit) {
        if (page < 1) {
            page = 1;
        }
        if (type == 0) {
            return suggestionDao.getSuggestionList((page - 1) * limit, limit);
        }
        return suggestionDao.getSuggestionListByType(type, (page - 1) * limit, limit);
    }

    public List<SuggestionEntity> getSuggestionListByStatus(int status, int page) {
        if (page < 1) {
            page = 1;
        }
        return suggestionDao.getSuggestionListByStatus(status, (page - 1) * 10);
    }

    public Integer getCount(int type) {
        if (type == 0) {
            return suggestionDao.getCount();
        } else {
            return suggestionDao.getCountByType(type);
        }
    }

    public Integer getCountByStatus(int status) {
        return suggestionDao.getCountByStatus(status);
    }

    public SuggestionEntity addSuggestion(String content, int type, String userName, String userAccount, String ip) {
        if (StringUtils.isBlank(content)) {
            throw new InvalidParamsException("content");
        }
        if (StringUtils.isBlank(userName)) {
            throw new InvalidParamsException("user_name");
        }
        if (type != SuggestionEntity.TYPE_OPINION && type != SuggestionEntity.TYPE_SUGGESTION) {
            throw new InvalidParamsException("type");
        }
        SuggestionEntity suggestionEntity = new SuggestionEntity();
        suggestionEntity.setContent(content);
        suggestionEntity.setType(type);
        suggestionEntity.setUserName(userName);
        suggestionEntity.setUserAccount(userAccount);
        suggestionEntity.setUserIp(ip);
        suggestionEntity.setReply("");
        suggestionEntity.setCreateTime(Util.time());
        suggestionEntity.setUpdateTime(suggestionEntity.getCreateTime());

        return suggestionDao.save(suggestionEntity);

    }

    public void addReply(Long id, String reply) {
        if (!authenticationFacadeService.getAuthentication().hasAuthorizedSuggestion()) {
            throw new NotAllowedException("尚未授权!请联系管理员!");
        }
        SuggestionEntity suggestionEntity = suggestionDao.getById(id);
        if (suggestionEntity == null) {
            throw new NotExistsException();
        }
        if (suggestionEntity.getStatus() == SuggestionEntity.STATUS_REPLY) {
            throw new NotAllowedException();
        }
        if (StringUtils.isNotBlank(reply)) {
            suggestionEntity.setReply(reply);
            suggestionEntity.setStatus(SuggestionEntity.STATUS_REPLY);
            suggestionEntity.setUpdateTime(Util.time());
            suggestionEntity.setAdminId(authenticationFacadeService.getAuthentication().getId());
            suggestionDao.save(suggestionEntity);
        }
    }
}
