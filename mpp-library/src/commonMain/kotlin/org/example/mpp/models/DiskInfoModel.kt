package org.example.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class DiskInfoModel(
    val itotal: String,
    val device: String,
    val type: String,
    val dir: String,
    val free: String,
    val total: String
)