package com.example.mpp.helpers

import org.example.mpp.ServerBase
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual fun createDB(): ServerBase {
    val driver = NativeSqliteDriver(ServerBase.Schema, "ServerDB.db")
    return ServerBase(driver)
}