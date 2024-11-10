package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过 Spring 的 @Configuration 注解定义一个 Spring 配置类，并使用 @Bean 注解生成 AliOssUtil 对象，将其作为 Spring 容器中的一个 Bean。
 *
 * @Configuration：表示该类是一个配置类，类似于 Spring 的 XML 配置文件。
 * @Bean：将 AliOssUtil 的创建逻辑声明为一个 Bean，这样可以将 AliOssUtil 注入到其他需要它的地方。
 * @ConditionalOnMissingBean：如果 Spring 容器中没有该类型的 Bean，则创建 AliOssUtil Bean。
 */
@Slf4j
@Configuration
public class OssConfigration {
    @Bean//是 Spring 框架中的一个注解，用于在配置类中定义一个方法，该方法的返回值会被 Spring 容器管理为一个 Bean。
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties) {
        log.info("开始创建阿里云文件上传工具类对象，配置信息为：{}", aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint(), aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(), aliOssProperties.getBucketName());
    }

}
