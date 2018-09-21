package ch.keepcalm.cloud.notification.service

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "fcm")
class FcmSettings {

    var serviceAccountFile: String? = null
        get() = field
        set(value) {
            field = value
        }
}
