package ru.emeltsaykin.gitlabwebhook.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import ru.emeltsaykin.gitlabwebhook.gitlab.model.ApiMergeAction
import ru.emeltsaykin.gitlabwebhook.gitlab.model.ApiMergeWebhookDto
import ru.emeltsaykin.gitlabwebhook.messenger.LoggerMessengerClient
import ru.emeltsaykin.gitlabwebhook.messenger.MessengerClient

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}

fun Application.configureGitlabWebhooks() {
    val messengerClient: MessengerClient = LoggerMessengerClient()
    routing {
        post("gitlab/merge/webhook") { // Задаем путь для нашего запроса
            val request = call.receive<ApiMergeWebhookDto>() // Принимаем body запроса
            val message = when (request.objectAttributes.action) { // Обрабатываем действие с merge request
                ApiMergeAction.Open -> {
                    val reviewers = request.reviewers?.joinToString { "@${it.username}" } ?: "Empty"
                    "[Merge request](${request.objectAttributes.url}) was opened. Reviewers: $reviewers"
                }

                ApiMergeAction.Merge -> {
                    "[Merge request](${request.objectAttributes.url}) was merged."
                }

                ApiMergeAction.Update -> {
                    "[Merge request](${request.objectAttributes.url}) was updated."
                }

                null -> {
                    return@post call.respond(HttpStatusCode.NoContent)
                }
            }
            messengerClient.sendMessage(
                channelId = "68ff6cad-c31b-461c-855e-47323341fd9c",
                message = message
            )
            call.respond(HttpStatusCode.OK)
        }
    }
}