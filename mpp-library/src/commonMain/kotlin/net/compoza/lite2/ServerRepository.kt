package net.compoza.lite2

import com.squareup.sqldelight.Query

expect fun createDB(): Server?

open class ServerRepository {
    private val database = createDB()
    private val serverQueries = database?.serverQueries

    // Insert New Server
    fun insert(title: String, url: String, token: String) {
        serverQueries?.insertServer(title, url, token)
    }

    // Get All Servers
    fun list(): List<Servers> {
        return serverQueries?.selectAll()?.executeAsList()!!
    }

    // Get Server by ID
    fun get(id: Long): Query<Servers>? {
        return serverQueries?.selectServer(id)
    }

    // Update Server
    fun update(id: Long, title: String, url: String, token: String) {
        serverQueries?.updateServer(title, url, token, id)
    }

    // Delete Server
    fun delete(id: Long) {
        serverQueries?.deleteServer(id)
    }
}

//class SerRep(serverDAO: SerDAO) {
//    private val dao = serverDAO
//
//    fun insertServer(title: String, url: String, token: String) {
//        dao.insert(title, url, token)
//    }
//
//    fun getAllServers(): List<Servers> {
//        return dao.list()
//    }
//
//    fun getServer(id: Long): Query<Servers> {
//        return dao.get(id)
//    }
//
//    fun updateServer(id: Long, title: String, url: String, token: String) {
//        dao.update(id, title, url, token)
//    }
//
//    fun deleteServer(id: Long) {
//        dao.delete(id)
//    }
//}