package org.example.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class ServiceInfoModel(
    val name: String,
    val status: Int

)