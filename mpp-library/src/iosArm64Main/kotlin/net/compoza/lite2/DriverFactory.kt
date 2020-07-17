//package net.compoza.lite2
//
//import com.squareup.sqldelight.db.SqlDriver
//import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
//import net.compoza.lite2.Server
//
//actual class DriverFactory {
//    actual fun createDriver(): SqlDriver {
//        return NativeSqliteDriver(Server.Schema, "Server.db")
//    }
//}