package org.example.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class ProgsModel(
    val title: String,
    val value: String
)