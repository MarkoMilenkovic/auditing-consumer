package com.sqs.example.listener;

import com.sqs.example.service.VersionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SqsListener {


    private final VersionService versionService;

    @org.springframework.cloud.aws.messaging.listener.annotation.SqsListener(value = "https://sqs.eu-central-1.amazonaws.com/701917776751/lemilica", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processMessage(String message) {
        try {
            log.debug("Received new SQS message: {}", message );
            versionService.uploadJsonVersionToS3(message);
        } catch (Exception e) {
            throw new RuntimeException("Cannot process message from SQS", e);
        }
    }

}
