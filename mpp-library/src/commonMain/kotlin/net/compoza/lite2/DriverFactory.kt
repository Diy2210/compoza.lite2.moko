package net.compoza.lite2

import com.squareup.sqldelight.db.SqlDriver
import net.compoza.lite2.Server

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory : DriverFactory) : Server{
    val driver = driverFactory.createDriver()
    return Server(driver)
}
