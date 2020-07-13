package net.compoza.lite2.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class ProgsModel(
    val title: String,
    val value: String
)