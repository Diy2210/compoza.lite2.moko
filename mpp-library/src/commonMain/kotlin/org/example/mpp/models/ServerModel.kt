package org.example.mpp.models

import kotlinx.serialization.*

@Serializable
data class ServerModel(
    val hostname: String = "",
    val os: String = "",
    val ip: Double,
    val kernel: String = "",
    val uptime: String = "",
    val date: String = ""
)