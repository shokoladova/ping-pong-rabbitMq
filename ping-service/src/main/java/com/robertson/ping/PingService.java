package com.robertson.ping;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author Radik Akhmadeev
 * @since 27.07.2017
 */
@Service
@EnableBinding({PongListener.class})
public class PingService {

    private int pingCount;

    private MessageChannel pingSender;

    public PingService(PingProducer producer) {
        this.pingSender = producer.producer();
    }

    public void sendPingMessage() throws InterruptedException {
        Message<Integer> msg = MessageBuilder.withPayload(++pingCount).build();
        pingSender.send(msg);
        Thread.sleep(1000);
    }

    @StreamListener("pong")
    public void onPingMessage(Message<Integer> msg) throws InterruptedException {
        System.out.println("pong " + msg.getPayload());
        Thread.sleep(1000);
        sendPingMessage();
    }
}
