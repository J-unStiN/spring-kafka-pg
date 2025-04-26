package com.ex.springkafkapg.kafka

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping( "/kafka")
class KafkaController(private val producer: KafkaProducer) {


    @PostMapping(value = ["/publish"])
    fun sendMessage(
        @RequestParam("topic") topic: String,  // 요청 파라미터로 토픽 이름 받기
        @RequestParam("message") message: String
    ): ResponseEntity<String> { // 요청 파라미터로 메시지 내용 받기
        try {
            this.producer.sendMessage(topic, message) // KafkaProducer 서비스를 이용해 메시지 발행
            val responseMessage = String.format("Message '%s' sent successfully to topic '%s'", message, topic)
            return ResponseEntity.ok(responseMessage) // 성공 응답 반환
        } catch (e: Exception) {
            val errorMessage = String.format("Error sending message to topic '%s': %s", topic, e.message)
            // 실제 운영 환경에서는 로깅을 추가하는 것이 좋습니다.
            return ResponseEntity.internalServerError().body(errorMessage) // 에러 발생 시 500 응답 반환
        }
    }

}