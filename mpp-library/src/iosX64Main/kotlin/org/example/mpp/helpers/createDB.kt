package org.example.mpp.helpers

import com.squareup.sqldelight.db.SqlDriver
import org.example.mpp.ServerBase
import kotlin.properties.Delegates

import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver

actual fun createDB(): ServerBase {
    val driver = NativeSqliteDriver(ServerBase.Schema, "serverdb.db")
    return ServerBase(driver)
    return ServerBase(driver)
}