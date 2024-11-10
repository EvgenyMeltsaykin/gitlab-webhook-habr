package ru.emeltsaykin.gitlabwebhook

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import ru.emeltsaykin.gitlabwebhook.plugins.configureGitlabWebhooks
import ru.emeltsaykin.gitlabwebhook.plugins.configureRouting
import ru.emeltsaykin.gitlabwebhook.plugins.configureSerialization

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureGitlabWebhooks()
}
