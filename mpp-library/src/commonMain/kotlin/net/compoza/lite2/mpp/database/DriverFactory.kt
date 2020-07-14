package net.compoza.lite2.mpp.database

import com.squareup.sqldelight.db.SqlDriver
import net.compoza.lite2.Server

expect class DriverFactory {
    expect fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory) {
    val driver = driverFactory.createDriver()
    val database = Server(driver)

}