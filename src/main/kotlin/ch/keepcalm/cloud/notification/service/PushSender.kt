package ch.keepcalm.cloud.notification.service

import ch.keepcalm.cloud.notification.integration.service.IcndbClient
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.unbescape.html.HtmlEscape
import java.util.*
import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.ExecutionException

@Service
class PushSender(private val fcmClient: FcmClient, private val icndbClient : IcndbClient) {

    private final val log = LoggerFactory.getLogger(PushSender::class.java)


    private val tokenRegistry = CopyOnWriteArraySet<String>()

    private var id = 0

    fun addToken(token: String) {
        this.tokenRegistry.add(token)
    }

    fun removeToken(token: String) {
        this.tokenRegistry.remove(token)
    }

    @Scheduled(fixedDelay = 30000)
    internal fun sendPushMessages() {
        for (token in this.tokenRegistry) {
            println("Sending personal message to: $token")
            val data = HashMap<String, String>()
            data["id"] = (++this.id).toString()
            data["text"] = (Math.random() * 1000).toString()

            try {
                this.fcmClient.sendPersonalMessage(token, data)
            } catch (e: InterruptedException) {
                log.error("send personal message", e)
            } catch (e: ExecutionException) {
                log.error("send personal message", e)
            }

        }
    }


    @Scheduled(fixedDelay = 30000)
    fun sendChuckQuotes() {
        sendPushMessage(HtmlEscape.unescapeHtml(icndbClient.getChuckQuotes()))
    }

    internal fun sendPushMessage(joke: String) {
        val data = HashMap<String, String>()
        data["id"] = (++this.id).toString()
        data["text"] = joke

        // Send a message
        println("Sending chuck joke...")
        try {
            this.fcmClient.sendJoke(data)
        } catch (e: InterruptedException) {
            log.error("send joke", e)
        } catch (e: ExecutionException) {
            log.error("send joke", e)
        }

    }

}