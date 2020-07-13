package net.compoza.lite2.mpp.models

import kotlinx.serialization.*

@Serializable
data class HostModel(
    var hostname: String,
    var os: String,
    var ip: String,
    var kernel: String,
    var uptime: String,
    var updates: Int,
    var date: String
)
