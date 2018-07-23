package cn.wenqi.oauth2.web.controller;

import cn.wenqi.oauth2.constant.CommonConstant;
import cn.wenqi.oauth2.entity.IResources;
import cn.wenqi.oauth2.entity.PageInfo;
import cn.wenqi.oauth2.web.conf.ApiServerProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author wenqi
 * @since v1.0.0
 */
@Controller
@RequestMapping("/resources")
@Slf4j
public class ResourceController {

    @Autowired
    private RestTemplate restTemplate;

    private final ApiServerProps apiServerProps;

    public ResourceController(ApiServerProps apiServerProps){
        this.apiServerProps=apiServerProps;
    }

    @RequestMapping("/manage")
    public String page(){
        return "resources";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,String desc) throws IOException {
        String filePath=tempStore(file);
        Resource resource=new FileSystemResource(filePath);
        HttpHeaders httpHeaders=new HttpHeaders();
        MediaType mediaType=MediaType.parseMediaType("multipart/form-data;charset=UTF-8");
        httpHeaders.setContentType(mediaType);
        MultiValueMap<String,Object> valueMap=new LinkedMultiValueMap<>();
        valueMap.add("file",resource);
        valueMap.add("desc",desc);
        HttpEntity<MultiValueMap<String,Object>> reqEntity=new HttpEntity<>(valueMap,httpHeaders);
        ResponseEntity<String> responseEntity=restTemplate.exchange(apiServerProps.getUrl()+"/res/add",HttpMethod.POST,reqEntity,String.class);
        Files.delete(Paths.get(filePath));
        assert CommonConstant.SUCCESS.equals(responseEntity.getBody());
        return ResponseEntity.ok("上传成功");
    }

    private String tempStore(MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        String ext=fileName.substring(fileName.lastIndexOf("."));
        String filePath= "/Users/wenqi/Develop/tmp/"+System.currentTimeMillis()+ext;
        Files.write(Paths.get(filePath),file.getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        return filePath;
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Resource> getResources(@PathVariable("id") Integer id){
        ResponseEntity<Resource> responseEntity=restTemplate
                .getForEntity(apiServerProps.getUrl()+"/res/get/"+id,Resource.class);
        return ResponseEntity.ok(responseEntity.getBody());
    }

    @GetMapping("/list")
    public ResponseEntity<PageInfo> getList(@RequestParam(defaultValue = "0") Integer pageNo,
                                            @RequestParam(defaultValue = "10") Integer pageSize){
        ParameterizedTypeReference<PageInfo<IResources>> typeRef = new ParameterizedTypeReference<PageInfo<IResources>>() {};
        ResponseEntity<PageInfo<IResources>> entity=restTemplate
                .exchange(apiServerProps.getUrl()+"/res/list?pageNo="+pageNo+"&pageSize="+pageSize,
                HttpMethod.GET,null,typeRef);
        assert entity.getStatusCode()!=HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.ok(entity.getBody());
    }
}
