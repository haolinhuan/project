package com.service.manage.client.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsPageDao extends MongoRepository<CmsPage,String> {


}
