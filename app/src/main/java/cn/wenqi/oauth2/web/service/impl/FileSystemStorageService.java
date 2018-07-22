package cn.wenqi.oauth2.web.service.impl;

import cn.wenqi.oauth2.constant.CommonConstant;
import cn.wenqi.oauth2.entity.IResources;
import cn.wenqi.oauth2.web.conf.ApiServerProps;
import cn.wenqi.oauth2.web.exception.StorageException;
import cn.wenqi.oauth2.web.exception.StorageFileNotFoundException;
import cn.wenqi.oauth2.web.conf.StorageProperties;
import cn.wenqi.oauth2.web.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.stream.Stream;

/**
 * @author wenqi
 * @since v1.0.1
 */
@Service
@Slf4j
public class FileSystemStorageService implements StorageService {

    @Autowired
    private OAuth2RestTemplate restTemplate;

    @Autowired
    private ApiServerProps apiServerProps;

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public String store(MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        String ext=fileName.substring(fileName.lastIndexOf("."));
        String filePath= rootLocation.toString()+"/"+System.currentTimeMillis()+ext;
        Files.write(Paths.get(filePath),file.getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);

        IResources iResources=new IResources();
        iResources.setExt(ext);
        iResources.setName(filePath);
        iResources.setCreateTime(new Date());
        ResponseEntity<String> responseEntity=restTemplate.postForEntity(apiServerProps.getUrl()+"/res/add",iResources,String.class);
        assert CommonConstant.SUCCESS.equals(responseEntity.getBody());
        return filePath;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        Resource resource = new FileSystemResource(load(filename).toFile());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        }
        else {
            throw new StorageFileNotFoundException("Could not read file: " + filename);

        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
