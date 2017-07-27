package com.robertson.pong;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author Radik Akhmadeev
 * @since 27.07.2017
 */

public interface PongProducer {

    @Output("pong")
    MessageChannel producer();
}
