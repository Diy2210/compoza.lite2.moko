//package net.compoza.lite2
//
//import com.squareup.sqldelight.Query
//import com.squareup.sqldelight.db.SqlDriver
//
//expect class DriverFactory {
//    fun createDriver(): SqlDriver
//}
//
//fun createDatabase(driverFactory: DriverFactory) : Server {
//    val driver = driverFactory.createDriver()
//    return Server(driver)
//}
//
//class ServerDAO(database: Server) {
//    private val db = database.serverQueries
//
//    // Insert New Server
//    fun insert(title: String, url: String, token: String) {
//        db.insertServer(title, url, token)
//    }
//
//    // Get All Servers
//    fun list() : List<Servers> {
//        return db.selectAll().executeAsList()
//    }
//
//    // Get Server by ID
//    fun get(id: Long) : Query<Servers> {
//        return db.selectServer(id)
//    }
//
//    // Update Server
//    fun update(id: Long, title: String, url: String, token: String) {
//        db.updateServer(title, url, token, id)
//    }
//
//    // Delete Server
//    fun delete(id: Long) {
//        db.deleteServer(id)
//    }
//}
//
//class ServerRep(serverDAO: ServerDAO) {
//    private val dao = serverDAO
//
//    fun insertServer(title: String, url: String, token: String) {
//        dao.insert(title, url, token)
//    }
//
//    fun getAllServers() : List<Servers> {
//        return dao.list()
//    }
//
//    fun getServer(id: Long) : Query<Servers> {
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