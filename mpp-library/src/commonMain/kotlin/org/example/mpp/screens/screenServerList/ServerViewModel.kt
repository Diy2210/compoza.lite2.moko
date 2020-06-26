package org.example.mpp.screens.screenServerList

import com.russhwolf.settings.Settings
import com.russhwolf.settings.invoke
import com.russhwolf.settings.set
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.example.mpp.api.CompozaApi
import org.example.mpp.models.ResponseModel
import org.example.mpp.screens.screenServerList.ServerItem as ServerModel
import kotlinx.coroutines.launch
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.*

class ServerViewModel(
    override val eventsDispatcher: EventsDispatcher<EventsListener>
) : ViewModel(), EventsDispatcherOwner<ServerViewModel.EventsListener> {

    val settings: Settings = Settings()
    var id = settings.getInt("Server ID")
    var title = settings.getString("Server Title")
    var url = settings.getString("Server Url")
    var token = settings.getString("Server Token")

//    val client = CompozaApi()

//    private lateinit var resObject: ResponseModel

    private val _servers: MutableLiveData<List<ServerModel>> =
        MutableLiveData(
            initialValue = List(1) {
                ServerModel(
                    ID = id,
                    title = title,
                    url = url,
                    token = token
                )
            }
        )

    val servers: LiveData<List<ServerModel>> = _servers

    fun onAddPressed() {
        eventsDispatcher.dispatchEvent {
            routeToEditServer()
        }
    }

    fun onClickToItem(model: ServerModel) {
        eventsDispatcher.dispatchEvent {
//            viewModelScope.launch {
//                try {
//                    client.getStatusServer(url, "/api/v1.1/info", token).also { response ->
//                        if(response.contains("success")) {
//                            val json = Json(JsonConfiguration.Default)
//                            resObject = json.parse(ResponseModel.serializer(), response)
//                            routeToDetails()
////                            settings.putString("hostname", (resObject as ResponseModel).data.host.hostname)
////                            settings.putString("date", (resObject as ResponseModel).data.host.date)
////                            settings.putString("ip", (resObject as ResponseModel).data.host.ip)
////                            settings.putString("kernel", (resObject as ResponseModel).data.host.kernel)
////                            settings.putString("os", (resObject as ResponseModel).data.host.os)
////                            settings.putString("uptime", (resObject as ResponseModel).data.host.uptime)
////                            settings.putInt("updates", (resObject as ResponseModel).data.host.updates)
////                            settings["disk_fs"] = (resObject as ResponseModel).data.disk_fs
//                        } else {
//                            println("SERVER ERROR")
//                        }
////                        routeToDetails(resObject)
//                    }
//                } catch (e: Exception) {
//                    println(e)
//                }
//            }
            routeToDetails()
        }
    }

    interface EventsListener {
        fun routeToEditServer()
        fun routeToDetails()
    }
}