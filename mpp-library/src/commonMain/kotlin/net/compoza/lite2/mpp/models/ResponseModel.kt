package net.compoza.lite2.mpp.models

import kotlinx.serialization.Serializable
import net.compoza.lite2.mpp.models.DataModel

@Serializable
data class ResponseModel(
    val success: Boolean,
    val data: DataModel
)