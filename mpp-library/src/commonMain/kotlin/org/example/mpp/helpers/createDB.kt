package org.example.mpp.helpers

import com.squareup.sqldelight.db.SqlDriver
import org.example.mpp.ServerBase

expect fun createDB(): ServerBase

class Helper() {
    val database = createDB()
    val serverDBQueries = database.serverDBQueries

    fun insertServer(title: String, url: String, token: String) {
        serverDBQueries.insertServer(title = "", url = "", token = "")
    }
}