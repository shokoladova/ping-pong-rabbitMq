package com.robertson.pong;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author Radik Akhmadeev
 * @since 27.07.2017
 */

public interface PingListener {
    @Input("ping")
    SubscribableChannel consumer();

}
