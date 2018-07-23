package cn.wenqi.oauth2.web.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wenqi
 * @since v
 */
@ConfigurationProperties("api")
@Component
@Data
public class ApiServerProps {
    /**
     * api服务所在url
     */
    private String url;
}
