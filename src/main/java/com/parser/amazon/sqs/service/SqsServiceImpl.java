package com.parser.amazon.sqs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import static com.parser.util.Constants.*;

@Service
@RequiredArgsConstructor
public class SqsServiceImpl implements SqsService {
    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;
    private final QueueMessagingTemplate sqs;

    public void putMessageInQueue(String payload, String userEmail, String userId, String input) {
        Message<String> msg = MessageBuilder.withPayload(payload)
                .setHeader(SENDER_HEADER, userId)
                .setHeader(USER_EMAIL_HEADER, userEmail)
                .setHeader(USER_INPUT_HEADER, input)
                .build();
        sqs.send(endPoint, msg);
    }
}