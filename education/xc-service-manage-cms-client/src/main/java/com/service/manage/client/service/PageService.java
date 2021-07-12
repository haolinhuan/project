package com.service.manage.client.service;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.service.manage.client.dao.CmsPageDao;
import com.service.manage.client.dao.CmsSiteDao;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.exception.ExceptionCast;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Optional;

@Service
public class PageService {

    @Autowired
    CmsPageDao cmsPageDao;

    @Autowired
    CmsSiteDao cmsSiteDao;

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    //将页面html保存到页面的物理路径
    public void savePageToServerPath(String pageId) {
        Optional<CmsPage> optional = cmsPageDao.findById(pageId);
        if (!optional.isPresent()) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }
        //取出页面物理路径
        CmsPage cmsPage = this.getCmsPageById(pageId);

        CmsSite cmsSite = this.getCmsSiteById(cmsPage.getSiteId());

        //页面的物理路径
        String htmlPath = cmsSite.getSitePhysicalPath()+cmsPage.getPagePhysicalPath()+cmsPage.getPageName();

        String htmlFileId = cmsPage.getHtmlFileId();

        InputStream inputStream = this.getFileById(htmlFileId);

        if(inputStream == null){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }
        FileOutputStream out = null;

        try{
            out = new FileOutputStream(new File(htmlPath));
            //将文件内容保存到服务的物理路径
            IOUtils.copy(inputStream,out);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public InputStream getFileById(String htmlFileId) {
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(htmlFileId)));

                //打开下载流
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(file.getObjectId());

        GridFsResource gridFsResource = new GridFsResource(file,gridFSDownloadStream);
        try {
            return gridFsResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public CmsSite getCmsSiteById(String siteId) {
        Optional<CmsSite> optional = cmsSiteDao.findById(siteId);
        if (optional.isPresent()) {
            CmsSite cmsSite = optional.get();
            return cmsSite;
        }
        return null;
    }

    public CmsPage getCmsPageById(String siteId) {
        Optional<CmsPage> optional = cmsPageDao.findById(siteId);
        if (optional.isPresent()) {
            CmsPage cmsPage = optional.get();
            return cmsPage;
        }
        return null;
    }


}
