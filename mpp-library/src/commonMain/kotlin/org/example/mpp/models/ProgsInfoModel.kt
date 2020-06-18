package org.example.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class ProgsInfoModel(
    val name: String,
    val status: Int
)