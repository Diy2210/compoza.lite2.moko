package org.example.mpp

import com.squareup.sqldelight.db.SqlDriver

class ServerBase {
    fun createDatabase(driver: SqlDriver): ServerBase {
    return ServerBase()
    }
}
//        val coordinateAdapter = object : ColumnAdapter<Server, String> {
//
//        }
//
//        override fun decode(databaseValue: String): Coordinate {
//            TODO("Not yet implemented")
//        }
//
//        override fun encode(value: Coordinate): String {
//            TODO("Not yet implemented")
//        }
//    }

//    val database = Server(driver)
//    println(ser.selectAll().executeAsList())
//// Prints [HockeyPlayer.Impl(15, "Ryan Getzlaf")]
//
//    playerQueries.insert(player_number = 10, full_name = "Corey Perry")
//    println(playerQueries.selectAll().executeAsList())
//// Prints [HockeyPlayer.Impl(15, "Ryan Getzlaf"), HockeyPlayer.Impl(10, "Corey Perry")]
//
//    val player = HockeyPlayer(10, "Ronald McDonald")
//    playerQueries.insertFullPlayerObject(player)
//}