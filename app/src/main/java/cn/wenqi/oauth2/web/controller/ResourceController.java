package cn.wenqi.oauth2.web.controller;

import cn.wenqi.oauth2.web.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author wenqi
 * @since v1.0.0
 */
@Controller
@RequestMapping("/resources")
public class ResourceController {

    @Autowired
    private StorageService storageService;

    @RequestMapping("/manage")
    public String page(){
        return "resources";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        storageService.store(file);
        return ResponseEntity.ok("上传成功");
    }

    @GetMapping("/get")
    public ResponseEntity<Resource> getResources(String name){
       return ResponseEntity.ok(storageService.loadAsResource(name)) ;
    }
}
