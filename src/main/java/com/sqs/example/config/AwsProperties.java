package com.sqs.example.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "aws")
@Configuration
@Getter
@Setter
public class AwsProperties {

    private String accessKey;
    private String secretKey;
    private String sqsQueue;

}