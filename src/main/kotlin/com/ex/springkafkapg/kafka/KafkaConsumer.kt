package com.ex.springkafkapg.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer {


    companion object {
        val logger = org.slf4j.LoggerFactory.getLogger(KafkaConsumer::class.java)
    }


    @KafkaListener(topics = ["my-topic"], groupId = "my-group")
    fun listen(message: String) {
        logger.info("Received message: ${message}")
    }

}