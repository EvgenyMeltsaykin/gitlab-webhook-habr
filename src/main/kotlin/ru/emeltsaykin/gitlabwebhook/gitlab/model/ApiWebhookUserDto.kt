package ru.emeltsaykin.gitlabwebhook.gitlab.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiWebhookUserDto(
    @SerialName("email") val email: String?,
    @SerialName("username") val username: String,
)