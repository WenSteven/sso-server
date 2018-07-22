package cn.wenqi.oauth2.service.api;

import cn.wenqi.oauth2.constant.CommonConstant;
import cn.wenqi.oauth2.entity.IResources;
import cn.wenqi.oauth2.service.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author wenqi
 * @since v1.0.1
 */
@RestController
@RequestMapping("/res")
public class ResourcesApi {


    @Autowired
    private IResourceService IResourceService;


    @PostMapping("/add")
    public ResponseEntity<String> addResources(@RequestBody IResources iResources, Principal principal){
        IResourceService.add(iResources);
        return ResponseEntity.ok(CommonConstant.SUCCESS);
    }

}
