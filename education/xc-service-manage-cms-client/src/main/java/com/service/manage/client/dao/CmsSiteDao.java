package com.service.manage.client.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsSiteDao extends MongoRepository<CmsSite,String> {
}
