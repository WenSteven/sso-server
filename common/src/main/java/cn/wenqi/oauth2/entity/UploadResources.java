package cn.wenqi.oauth2.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wenqi
 * @since v1.0.0
 */
@Data
public class UploadResources extends IResources {

    private MultipartFile file;
}
