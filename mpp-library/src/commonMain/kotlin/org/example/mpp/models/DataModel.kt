package org.example.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class DataModel (
    val status: StatusModel,
    val host: HostInfoModel,
//    val progs: ProgsInfoModel,
    val disk: DiskInfoModel
)