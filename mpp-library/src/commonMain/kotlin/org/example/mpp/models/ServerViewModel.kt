package org.example.mpp.models

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.invoke
import com.russhwolf.settings.string
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.widgets.ClickableWidget
import kotlinx.coroutines.launch
import org.example.mpp.api.CompozaApi
import org.example.mpp.models.ServerModel as ServerModel1

class ServerViewModel(
    override val eventsDispatcher: EventsDispatcher<EventsListener>
) : ViewModel(), EventsDispatcherOwner<ServerViewModel.EventsListener> {

    val settings: Settings = Settings()
    val client = CompozaApi()

    var id = settings.getInt("Server ID")
    var title = settings.getString("Server Title")
    var url = settings.getString("Server Url")
    var token = settings.getString("Server Token")

    private val _servers: MutableLiveData<List<ServerModel1>> =
        MutableLiveData(
            initialValue = List(1) {
                ServerModel1(
//                    ID = it,
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

    fun onClickToItem(model: ServerModel1) {
        eventsDispatcher.dispatchEvent {
            viewModelScope.launch {
                try {
                    client.getStatusServer(url, "/api/info", token).also { response ->
                        println(response)
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