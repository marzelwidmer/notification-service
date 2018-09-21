package ch.keepcalm.cloud.notification.service

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.time.Duration
import java.util.concurrent.ExecutionException


@Service
class FcmClient(fcmSettings: FcmSettings) {

    private final val log = LoggerFactory.getLogger(FcmClient::class.java)

    init {
        val p = Paths.get(fcmSettings.serviceAccountFile)
        try {
            Files.newInputStream(p).use { serviceAccount ->
                val options = FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://myhelsana.firebaseio.com").build()

                FirebaseApp.initializeApp(options)
            }
        } catch (e: IOException) {
            log.error("init fcm", e)
        }
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    fun sendJoke(data: Map<String, String>) {

        val androidConfig = AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey("chuck")
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setTag("chuck").build()).build()

        val apnsConfig = ApnsConfig.builder()
                .setAps(Aps.builder().setCategory("chuck").setThreadId("chuck").build()).build()

        val message = Message.builder().putAllData(data).setTopic("chuck")
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
                .setNotification(
                        Notification("Chuck Norris Joke", "A new Chuck Norris joke has arrived"))
                .build()

        val response = FirebaseMessaging.getInstance().sendAsync(message).get()
        println("Sent message: $response")
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    fun sendPersonalMessage(clientToken: String, data: Map<String, String>) {
        val androidConfig = AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey("personal")
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setTag("personal").build())
                .build()

        val apnsConfig = ApnsConfig.builder()
                .setAps(Aps.builder().setCategory("personal").setThreadId("personal").build())
                .build()

        val message = Message.builder().putAllData(data).setToken(clientToken)
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
                .setNotification(Notification("Personal Message", "A Personal Message"))
                .build()

        val response = FirebaseMessaging.getInstance().sendAsync(message).get()
        println("Sent message: $response")
    }
}

