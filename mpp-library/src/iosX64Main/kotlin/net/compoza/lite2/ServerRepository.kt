package net.compoza.lite2

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual fun createDB(): Server? {
    val driver = NativeSqliteDriver(Server.Schema, "Server.db")
    return Server(driver)
}
