package com.sqs.example.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqs.example.dto.Versionable;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class VersionService {

    private final AmazonS3 s3;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void uploadJsonVersionToS3(String messageJson) {
        log.info("uploading new version: {}", messageJson);
        Versionable versionable = objectMapper.readValue(messageJson, Versionable.class);
        versionable.setNewValueJson(messageJson);
        String fileName = String.format("%s-%d-%d.json", versionable.getSimpleName(), versionable.getId(), versionable.getVersion());
        byte[] bytes = objectMapper.writeValueAsBytes(versionable);
        ObjectMetadata omd = new ObjectMetadata();
        omd.setContentLength(bytes.length);
        String bucketName = "auditingpractice";
        s3.putObject(bucketName, fileName, new ByteArrayInputStream(bytes), omd);
        log.info("uploaded new version: {}", messageJson);
    }

}
