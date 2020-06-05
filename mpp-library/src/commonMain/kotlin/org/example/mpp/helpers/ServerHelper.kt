package org.example.mpp.helpers

import org.example.mpp.ServerBase

expect fun createDb() : ServerBase

class ServerHelper {
    private val database = createDb()
    private val serverDBQueries = database.serverDBQueries

    fun insertServer(title: String, url: String, token: String) {
        serverDBQueries.insertServer(title = "", url = "", token = "")
    }
}