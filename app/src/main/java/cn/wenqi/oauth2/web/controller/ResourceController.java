package cn.wenqi.oauth2.web.controller;

import cn.wenqi.oauth2.entity.Clicker;
import cn.wenqi.oauth2.entity.IResources;
import cn.wenqi.oauth2.entity.PageInfo;
import cn.wenqi.oauth2.entity.Users;
import cn.wenqi.oauth2.web.repository.ClickerRepository;
import cn.wenqi.oauth2.web.service.IResourceService;
import cn.wenqi.oauth2.web.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

/**
 * @author wenqi
 * @since v1.0.0
 */
@Controller
@RequestMapping("/resources")
@Slf4j
public class ResourceController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private IResourceService iResourceService;

    @Autowired
    private ClickerRepository clickerRepository;

    @RequestMapping("/manage")
    public String page(){
        return "resources";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,String desc) throws IOException {
        String path=storageService.store(file,desc);
        log.info("存储path是：{}",path);
        return ResponseEntity.ok("上传成功");
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Resource> getResources(@PathVariable("id") Integer id, Principal principal){
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
    public ResponseEntity<PageInfo> getList(@RequestParam(defaultValue = "0") Integer pageNo,
                                            @RequestParam(defaultValue = "10") Integer pageSize){
        return ResponseEntity.ok(iResourceService.select(pageNo, pageSize));
    }
}
