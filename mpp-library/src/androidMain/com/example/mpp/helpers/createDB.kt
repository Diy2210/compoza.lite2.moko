package org.example.mpp.helpers

import android.content.Context
import org.example.mpp.ServerBase
import com.squareup.sqldelight.android.AndroidSqliteDriver

lateinit var appContext: Context

actual fun createDB(): ServerBase? {
    val driver = NativeSqliteDriver(ServerBase.Schema, appContext, "ServerDB.db")
    return ServerBase(driver)
}