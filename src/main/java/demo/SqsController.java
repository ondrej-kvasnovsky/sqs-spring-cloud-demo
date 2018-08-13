package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sqs")
public class SqsController {

    private static final Logger LOG = LoggerFactory.getLogger(SqsController.class);
    private static final String QUEUE_NAME = "MessageProcessingQueue";

    private final QueueMessagingTemplate queueMessagingTemplate;

    public SqsController(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    @RequestMapping(value = "/message-processing-queue", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void sendMessageToMessageProcessingQueue(@RequestBody MyMessage message) {
        LOG.info("Going to send message {} over SQS", message);

        this.queueMessagingTemplate.convertAndSend(QUEUE_NAME, message);
    }

    @SqsListener(QUEUE_NAME)
    private void receiveMessage(MyMessage message,
                                @Header("ApproximateFirstReceiveTimestamp")
                                        String approximateFirstReceiveTimestamp) {
        LOG.info("Received SQS message {}", message);
    }

}
