package com.ex.springkafkapg.kafka

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    companion object {
        val logger = LoggerFactory.getLogger(KafkaProducer::class.java)
    }

    fun sendMessage(topic: String, message: String) {
        logger.info("Sending message: ${message} + to topic: ${topic}")
        kafkaTemplate.send(topic, message)
    }

}