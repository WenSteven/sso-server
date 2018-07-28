package cn.wenqi.oauth2.web.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wenqi
 * @since v
 */
@ConfigurationProperties("url")
@Component
@Data
public class UrlSettings {
    /**
     * api服务所在url
     */
    private String api;
    /**
     * 授权中心地址
     */
    private String oauth2;
}
