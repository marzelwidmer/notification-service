package ch.keepcalm.cloud.notification.integration.model

data class IcndbJoke(val type: String? = "",
                             val value: Value?)


data class Value(val id: Int = 0,
                 val categories: List<String>?,
                 val joke: String = "")


