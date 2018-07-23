package cn.wenqi.oauth2.service.service;

import cn.wenqi.oauth2.entity.IResources;
import cn.wenqi.oauth2.entity.PageInfo;

/**
 * @author wenqi
 * @since v
 */
public interface IResourceService {

    void add(IResources IResources);


    PageInfo<IResources> select(Integer pageNo, Integer pageSize);
}
