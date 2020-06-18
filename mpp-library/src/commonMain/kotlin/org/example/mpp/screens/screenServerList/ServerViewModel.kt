package org.example.mpp.screens.screenServerList

import com.russhwolf.settings.Settings
import com.russhwolf.settings.invoke
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.*
import kotlinx.serialization.parse
import org.example.mpp.api.CompozaApi
import org.example.mpp.models.HostInfoModel
import org.example.mpp.repositories.HostInfo
import org.example.mpp.screens.screenServerList.ServerItem as ServerModel1

class ServerViewModel(
    override val eventsDispatcher: EventsDispatcher<EventsListener>
) : ViewModel(), EventsDispatcherOwner<ServerViewModel.EventsListener> {

    val settings: Settings = Settings()
    var id = settings.getInt("Server ID")
    var title = settings.getString("Server Title")
    var url = settings.getString("Server Url")
    var token = settings.getString("Server Token")

    val client = CompozaApi()

    private val _servers: MutableLiveData<List<ServerModel1>> =
        MutableLiveData(
            initialValue = List(1) {
                ServerModel1(
                    ID = id,
                    title = title,
                    url = url,
                    token = token
                )
            }
        )

    val servers: LiveData<List<ServerModel1>> = _servers

    fun onAddPressed() {
        eventsDispatcher.dispatchEvent {
            routeToEditServer()
        }
    }

    @UnstableDefault
    @ImplicitReflectionSerializer
    fun onClickToItem(model: ServerModel1) {
        eventsDispatcher.dispatchEvent {
            viewModelScope.launch {
                try {
                    client.getStatusServer(url, "/api/info", token).also { response ->
                        println(response)

                        if(response.contains("success")) {
                            val json = Json(JsonConfiguration.Stable)
                            val res = json.parseJson(response)
                            val data: JsonObject = res.jsonObject["data"] as JsonObject
//                            val disk = DiskInfo.collect(data.jsonObject["disk"] as JsonObject)
                            val host = HostInfo.collect(data.jsonObject["host"] as JsonObject)
//                            val service = ServiceInfo.collect(data.jsonObject["service"] as JsonObject)
//                            val software = SoftwareInfo.collect(data.jsonObject["software"] as JsonObject)

                            val h = data.jsonObject["host"] as JsonObject
                            val hostToString = Json.parse<HostInfoModel>(Json.toJson(h).toString())

//                            toObject(h)

//                            settings.putString("host", data.jsonObject["host"].toString())

                            println("//////////$hostToString")
                        } else {
                            println("SERVER ERROR")
                        }
                        routeToDetails()
                    }
                } catch (e: Exception) {
                    println(e)
                }
            }
        }
    }

    interface EventsListener {
        fun routeToEditServer()
        fun routeToDetails()
    }

//    @UnstableDefault
//    fun toObject(stringValue: String): ServerModel {
//        return Json.parse(ServerModel.serializer(), stringValue)
//        println("//////////-" + Json.parse(ServerModel.serializer(), stringValue))
//    }
}