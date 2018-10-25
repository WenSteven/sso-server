package cn.wenqi.oauth2.web.service.impl;

import cn.wenqi.oauth2.entity.IResources;
import cn.wenqi.oauth2.entity.PageInfo;
import cn.wenqi.oauth2.web.repository.IResourcesRepository;
import cn.wenqi.oauth2.web.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    private  List<String> videos= Arrays.asList("mp4","rmvb","mov");

    private List<String> imgs=Arrays.asList("png","jpg","gif","jpeg");

    @Override
    public PageInfo<IResources> select(Integer pageNo, Integer pageSize) {
        // ext 0:其他  1：图片 2：视频
        Specification<IResources> specification= (root, criteriaQuery, criteriaBuilder) ->
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTime").as(Date.class))).getRestriction();
        Page<IResources> page= iResourcesRepository.findAll(specification, PageRequest.of(pageNo-1,pageSize));
        PageInfo<IResources> pageInfo=new PageInfo<>();
        List<IResources> iResources=page.getContent();
        iResources.parallelStream().forEach(r->{
                if(videos.contains(r.getExt().toLowerCase()))
                    r.setExt("2");
                else if(imgs.contains(r.getExt()))
                    r.setExt("1");
                else
                    r.setExt("0");
        });
        pageInfo.setData(page.getContent());
        pageInfo.setTotalCount(page.getTotalElements());
        pageInfo.setTotalPages(page.getTotalPages());
        return pageInfo;
    }

    @Override
    public IResources selectById(Integer id) {
        return iResourcesRepository.findById(id).orElse(null);
    }
}
