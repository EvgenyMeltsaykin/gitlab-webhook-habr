package ru.emeltsaykin.gitlabwebhook.gitlab.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiObjectAttributesDto(
    @SerialName("title") val title: String,
    @SerialName("action") val action: ApiMergeAction? = null,
    @SerialName("url") val url: String,
)