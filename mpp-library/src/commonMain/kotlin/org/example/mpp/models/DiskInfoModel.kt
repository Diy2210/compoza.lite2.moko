package org.example.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class DiskInfoModel(
    val dir: String = "",
    val free: String = "",
    val total: String = ""
)