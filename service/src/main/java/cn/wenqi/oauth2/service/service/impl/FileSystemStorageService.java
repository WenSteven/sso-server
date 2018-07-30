package cn.wenqi.oauth2.service.service.impl;

import cn.wenqi.oauth2.entity.IResources;
import cn.wenqi.oauth2.service.conf.StorageProperties;
import cn.wenqi.oauth2.service.exception.StorageException;
import cn.wenqi.oauth2.service.exception.StorageFileNotFoundException;
import cn.wenqi.oauth2.service.repository.IResourcesRepository;
import cn.wenqi.oauth2.service.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author wenqi
 * @since v1.0.1
 */
@Service
@Slf4j
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    private IResourcesRepository iResourcesRepository;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public String store(MultipartFile file, String desc) throws IOException {
        String fileName=file.getOriginalFilename();
        String ext=fileName.substring(fileName.lastIndexOf("."));
        String name=UUID.randomUUID().toString();
        String filePath= rootLocation.toString()+"/"+name+ext;
        Files.write(Paths.get(filePath),file.getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        log.info("已保存，开始写入db...");
        IResources iResources=new IResources();
        iResources.setCreateTime(new Date());
        iResources.setName(name);
        iResources.setExt(ext.substring(1));
        iResources.setDescribe(desc);
        iResourcesRepository.save(iResources);
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
        Path path=load(filename);
        log.info("加载资源的路劲是：{}",path.toString());
        Resource resource = new FileSystemResource(path.toFile());
        if (resource.exists() || resource.isReadable())
            return resource;
        else
            throw new StorageFileNotFoundException("Could not read file: " + filename);
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
