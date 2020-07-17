//package net.compoza.lite2
//
//import android.content.Context
//import com.squareup.sqldelight.db.SqlDriver
//import com.squareup.sqldelight.android.AndroidSqliteDriver
//
//actual class DriverFactory(private val context: Context) {
//    actual fun createDriver(): SqlDriver {
//        return AndroidSqliteDriver(Server.Schema, context, "Server.db")
//    }
//}