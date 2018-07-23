package cn.wenqi.oauth2.service.service.impl;

import cn.wenqi.oauth2.entity.IResources;
import cn.wenqi.oauth2.entity.PageInfo;
import cn.wenqi.oauth2.service.repository.IResourcesRepository;
import cn.wenqi.oauth2.service.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author wenqi
 * @since v
 */
@Service
public class IResourceServiceImpl implements IResourceService {

    @Autowired
    private IResourcesRepository iResourcesRepository;

    @Override
    public void add(IResources IResources) {
        iResourcesRepository.save(IResources);
    }

    @Override
    public PageInfo<IResources> select(Integer pageNo, Integer pageSize) {
        Page<IResources> page= iResourcesRepository.findAll(new PageRequest(pageNo-1,pageSize));
        PageInfo<IResources> pageInfo=new PageInfo<>();
        pageInfo.setData(page.getContent());
        pageInfo.setTotalCount(page.getTotalElements());
        pageInfo.setTotalPages(page.getTotalPages());
        return pageInfo;
    }

    @Override
    public IResources selectById(Integer id) {
        return iResourcesRepository.findOne(id);
    }
}
