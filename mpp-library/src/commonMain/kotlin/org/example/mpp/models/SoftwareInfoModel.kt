package org.example.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class SoftwareInfoModel (
    val operationSystem: String,
    val perlVersion: Float,
    val pathToPerl: String,
    val bindVersion: Float,
    val postfixVersion: Float,
    val mailCommand: String,
    val apacheVersion: Float,
    val phpVersion: Float,
    val webalizerVersion: Float,
    val logrotateVersion: Float,
    val mySQLVersion: Float,
    val proFTPDVersion: Float,
    val spamAssassinVersion: Float,
    val clamAVVersion: Float
)