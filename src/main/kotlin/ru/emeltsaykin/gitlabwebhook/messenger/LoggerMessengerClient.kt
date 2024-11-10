package ru.emeltsaykin.gitlabwebhook.messenger

import io.ktor.util.logging.Logger

class LoggerMessengerClient : MessengerClient {
    private val logger = System.getLogger(Logger.ROOT_LOGGER_NAME)
    override fun sendMessage(
        channelId: String, message: String
    ) {
        logger.log(System.Logger.Level.INFO, "Message receive to channel $channelId.\n$message")
    }
}