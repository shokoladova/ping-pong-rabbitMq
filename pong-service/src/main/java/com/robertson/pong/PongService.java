package com.robertson.pong;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author Radik Akhmadeev
 * @since 27.07.2017
 */
@EnableBinding({PingListener.class, PongProducer.class})
public class PongService {

    private int pongCount;

    private MessageChannel pongSender;

    public PongService(PongProducer producer) {
        this.pongSender = producer.producer();
    }

    @StreamListener("ping")
    public void onPingMessage(Message<Integer> msg) throws InterruptedException {
        System.out.println("ping " + msg.getPayload());
        Thread.sleep(1000);
        sendPongMessage();
    }

    public void sendPongMessage() throws InterruptedException {
        Message<Integer> msg = MessageBuilder.withPayload(++pongCount).build();
        pongSender.send(msg);
        Thread.sleep(1000);
    }
}
