package ch.keepcalm.cloud.notification.web.rest

import ch.keepcalm.cloud.notification.service.PushSender
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin
class PersonalMessageController(private val pushSender: PushSender) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun register(@RequestParam("token") token: String) {
        println("register: $token")
        this.pushSender.addToken(token)
    }

    @PostMapping("/unregister")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun unregister(@RequestParam("token") token: String) {
        println("unregister: $token")
        this.pushSender.removeToken(token)
    }

}
