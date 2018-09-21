package ch.keepcalm.cloud.notification

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class NotificationServiceApplication

fun main(args: Array<String>) {

    runApplication<NotificationServiceApplication>(*args)
}


