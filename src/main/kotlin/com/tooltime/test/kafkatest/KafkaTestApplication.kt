package com.tooltime.test.kafkatest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@SpringBootApplication
class KafkaTestApplication

fun main(args: Array<String>) {
	runApplication<KafkaTestApplication>(*args)
}

@RestController
class TestController(private val kafkaTemplate:KafkaTemplate<String, String>) {

	@KafkaListener(topics = ["test_topic"])
	fun receive(message: String) {
		println(message)
	}

	@GetMapping("/send")
	fun send() {
		kafkaTemplate.send("test_topic", "Time is ${Date()}")
	}

}