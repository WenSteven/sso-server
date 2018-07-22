package cn.wenqi.oauth2.service.service.impl;

import cn.wenqi.oauth2.entity.IResources;
import cn.wenqi.oauth2.service.repository.IResourcesRepository;
import cn.wenqi.oauth2.service.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wenqi
 * @since v
 */
@Service
public class IResourceServiceImpl implements IResourceService {

    @Autowired
    private IResourcesRepository IResourcesRepository;

    @Override
    public void add(IResources IResources) {
        IResourcesRepository.save(IResources);
    }
}
