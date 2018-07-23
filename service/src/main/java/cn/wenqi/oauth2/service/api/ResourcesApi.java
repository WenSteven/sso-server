package cn.wenqi.oauth2.service.api;

import cn.wenqi.oauth2.constant.CommonConstant;
import cn.wenqi.oauth2.entity.IResources;
import cn.wenqi.oauth2.entity.PageInfo;
import cn.wenqi.oauth2.entity.UploadResources;
import cn.wenqi.oauth2.service.service.IResourceService;
import cn.wenqi.oauth2.service.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

/**
 * @author wenqi
 * @since v1.0.1
 */
@RestController
@RequestMapping("/res")
@Slf4j
public class ResourcesApi {


    @Autowired
    private StorageService storageService;

    @Autowired
    private IResourceService iResourceService;


    @PostMapping("/add")
    public ResponseEntity<String> addResources(@RequestParam("file")MultipartFile file, Principal principal) throws IOException {
        storageService.store(file);
        log.info("认证信息是：{}",principal.getName());
        return ResponseEntity.ok(CommonConstant.SUCCESS);
    }


    @GetMapping("/get")
    public ResponseEntity<Resource> getResource(String name){
        return ResponseEntity.ok(storageService.loadAsResource(name));
    }

    @GetMapping("/list")
    public ResponseEntity<PageInfo<IResources>> getResList(Integer pageNo, Integer pageSize){
       return ResponseEntity.ok(iResourceService.select(pageNo, pageSize));
    }

}
