package cn.wenqi.oauth2.service.api;

import cn.wenqi.oauth2.constant.CommonConstant;
import cn.wenqi.oauth2.entity.*;
import cn.wenqi.oauth2.service.repository.ClickerRepository;
import cn.wenqi.oauth2.service.service.IResourceService;
import cn.wenqi.oauth2.service.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

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

    @Autowired
    private ClickerRepository clickerRepository;


    @PostMapping("/add")
    public ResponseEntity<String> addResources(@RequestParam("file")MultipartFile file,
                                               @RequestParam("desc") String desc,
                                               Principal principal) throws IOException {
        storageService.store(file, desc);
        log.info("认证信息是：{}",principal.getName());
        return ResponseEntity.ok(CommonConstant.SUCCESS);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Resource> getResource(@PathVariable("id") Integer id,Principal principal){
        IResources iResources=iResourceService.selectById(id);
        String name=iResources.getName()+"."+iResources.getExt();
        Clicker clicker=new Clicker();
        clicker.setClickTime(new Date());
        clicker.setIResources(iResources);
        Users users=new Users();
        users.setUserName(principal.getName());
        clicker.setUsers(users);
        clickerRepository.save(clicker);
        return ResponseEntity.ok(storageService.loadAsResource(name));
    }

    @GetMapping("/list")
    public ResponseEntity<PageInfo<IResources>> getResList(Integer pageNo, Integer pageSize){
       return ResponseEntity.ok(iResourceService.select(pageNo, pageSize));
    }

}
