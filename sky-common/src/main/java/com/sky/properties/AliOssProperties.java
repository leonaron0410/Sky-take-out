package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 该类用于读取 Spring Boot 配置文件 application.yml 中的 sky.alioss 配置项，
 * 具体配置包括阿里云 OSS 的 endpoint、accessKeyId、accessKeySecret 和 bucketName。
 *
 * @Component：将该类作为 Spring 的组件管理，让 Spring 容器可以自动加载它。
 * @ConfigurationProperties(prefix = "sky.alioss")：这个注解将 application.yml 文件中以 sky.alioss 开头的配置映射到这个类的属性中。
 */
@Component
@ConfigurationProperties(prefix = "sky.alioss")
@Data
public class AliOssProperties {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

}
