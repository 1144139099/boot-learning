package com.hlh.boot.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource(value = "classpath:aliyun.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "aliyun" )
public class AliyunResource {
    private String accessKeyId;
    private String accessKeySecret;
    private String templateCode;
    private String signName;
}
