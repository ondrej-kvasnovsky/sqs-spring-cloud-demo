package demo;

import cloud.localstack.docker.LocalstackDockerExtension;
import cloud.localstack.docker.annotation.LocalstackDockerProperties;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({LocalstackDockerExtension.class, SpringExtension.class})
@SpringBootTest
@LocalstackDockerProperties(randomizePorts = true, services = {"sqs"})
class SqsControllerTest {

    @Autowired
    SqsController controller;

    @Autowired
    AmazonSQS client;

    @BeforeEach
    void setUp() {
        Map<String, String> attributeMap = new HashMap<>();
        attributeMap.put("DelaySeconds", "0");
        attributeMap.put("MaximumMessageSize", "262144");
        attributeMap.put("MessageRetentionPeriod", "1209600");
        attributeMap.put("ReceiveMessageWaitTimeSeconds", "20");
        attributeMap.put("VisibilityTimeout", "30");
        CreateQueueRequest createQueueRequest = new CreateQueueRequest("MessageProcessingQueue").withAttributes(attributeMap);

        CreateQueueResult result = client.createQueue(createQueueRequest);

        assertNotNull(result);
    }

    @Test
    void sendMessageToMessageProcessingQueue() {
        controller.sendMessageToMessageProcessingQueue(new MyMessage("Hi", 1));
    }
}
