package ru.emeltsaykin.gitlabwebhook.gitlab.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMergeWebhookDto(
    @SerialName("object_attributes")
    val objectAttributes: ApiObjectAttributesDto,
    @SerialName("reviewers")
    val reviewers: List<ApiWebhookUserDto>? = null,
)