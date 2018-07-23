package cn.wenqi.oauth2.service.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author wenqi
 * @since v1.0.1
 */
public interface StorageService {

    void init();

    String store(MultipartFile file, String desc) throws IOException;

    Stream<Path> loadAll();

    Path load(String filename);

    /**
     * 文件名
     * @param filename
     * @return
     */
    Resource loadAsResource(String filename);

    void deleteAll();
}
