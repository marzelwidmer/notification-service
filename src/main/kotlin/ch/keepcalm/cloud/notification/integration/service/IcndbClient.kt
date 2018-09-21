package ch.keepcalm.cloud.notification.integration.service

import ch.keepcalm.cloud.notification.integration.model.IcndbJoke
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.unbescape.html.HtmlEscape


@Service
class IcndbClient {

    private final val log = LoggerFactory.getLogger(IcndbClient::class.java)

    private val restTemplate: RestTemplate

    private var id = 0

    init {
        this.restTemplate = RestTemplate()
    }

    fun getChuckQuotes(): String? {
        val joke = this.restTemplate.getForObject("http://api.icndb.com/jokes/random",
                IcndbJoke::class.java)
        return HtmlEscape.unescapeHtml(joke.toString())
    }

}