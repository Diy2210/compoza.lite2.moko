package org.example.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    val response: String = "",
    val data: String = ""
)