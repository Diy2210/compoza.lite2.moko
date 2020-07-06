package org.example.mpp.models

import dev.icerock.moko.units.TableUnitItem
import kotlinx.serialization.Serializable

@Serializable
data class DiskModel (
    val ifree: String,
    val itotal: String,
    val device: String,
    val type: String,
    val dir: String,
    val free: String,
    val total: String
)