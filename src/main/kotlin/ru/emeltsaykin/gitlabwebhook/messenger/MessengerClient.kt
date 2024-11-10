package ru.emeltsaykin.gitlabwebhook.messenger

interface MessengerClient {
    fun sendMessage(channelId: String, message: String)
}