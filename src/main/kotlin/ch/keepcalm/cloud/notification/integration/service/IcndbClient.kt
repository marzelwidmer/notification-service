package ch.keepcalm.cloud.notification.integration.service

import ch.keepcalm.cloud.notification.integration.model.IcndbJoke
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.unbescape.html.HtmlEscape


@Service
class IcndbClient {

    private final val log = LoggerFactory.getLogger(IcndbClient::class.java)

    fun getChuckQuotes(): String? {
        val joke = RestTemplate().getForObject("http://api.icndb.com/jokes/random",
                IcndbJoke::class.java)
        return HtmlEscape.unescapeHtml(joke.toString())
    }

}