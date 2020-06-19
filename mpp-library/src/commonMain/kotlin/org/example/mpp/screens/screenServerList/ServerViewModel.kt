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
import kotlinx.serialization.StructureKind
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.*
import org.example.mpp.api.CompozaApi
import org.example.mpp.models.ResponseModel
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
                    client.getStatusServer(url, "/api/v1.1/info", token).also { response ->
                        println(response)
                        if(response.contains("success")) {
                            val json = Json(JsonConfiguration.Default)
                            val resObject = json.parse(ResponseModel.serializer(), response)
                            settings.putString("hostname", resObject.data.host.hostname)
                            settings.putString("date", resObject.data.host.date)
                            settings.putString("ip", resObject.data.host.ip)
                            settings.putString("kernel", resObject.data.host.kernel)
                            settings.putString("os", resObject.data.host.os)
                            settings.putString("uptime", resObject.data.host.uptime)
                            settings.putInt("updates", resObject.data.host.updates)
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
}