package org.example.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    val success: Boolean,
    val data: DataModel
)