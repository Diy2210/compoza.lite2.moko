package org.example.mpp.models

import kotlinx.serialization.*

@Serializable
data class HostInfoModel(
    val hostname: String,
    val os: String,
    val ip: String,
    val kernel: String,
    val uptime: String,
    val updates: String,
    val date: String
)