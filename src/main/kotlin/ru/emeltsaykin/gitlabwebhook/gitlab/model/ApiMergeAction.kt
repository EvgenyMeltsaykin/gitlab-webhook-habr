package ru.emeltsaykin.gitlabwebhook.gitlab.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiMergeAction {
    @SerialName("open")
    Open,
    @SerialName("merge")
    Merge,
    @SerialName("update")
    Update;
}