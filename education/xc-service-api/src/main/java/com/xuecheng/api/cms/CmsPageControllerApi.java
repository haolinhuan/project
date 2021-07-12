package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;

public interface CmsPageControllerApi {

    @ApiOperation("页面查询")
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    //新增页面
    @ApiOperation("添加页面")
    public CmsPageResult add(CmsPage cmsPage);

    //新增页面
    @ApiOperation("根据ID查询对应的数据")
    public CmsPageResult findById(String id);

    //新增页面
    @ApiOperation("修改数据")
    public CmsPageResult edit(CmsPage cmsPage);


    @ApiOperation("通过ID删除页面")
    public ResponseResult delete(String id);


}
