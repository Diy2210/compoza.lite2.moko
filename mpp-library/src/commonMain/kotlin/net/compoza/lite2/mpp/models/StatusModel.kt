package net.compoza.lite2.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class StatusModel (
    val name: String,
    val status: String,
    val feature: String,
    val id: String? = null
)