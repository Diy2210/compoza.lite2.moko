package org.example.mpp.models

import com.russhwolf.settings.Settings
import com.russhwolf.settings.invoke
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.widgets.ClickableWidget

class ServerViewModel(
    override val eventsDispatcher: EventsDispatcher<EventsListener>
): ViewModel(), EventsDispatcherOwner<ServerViewModel.EventsListener> {

    val settings: Settings = Settings()

    private val _servers: MutableLiveData<List<ServerModel>> =
        MutableLiveData(
            initialValue = List(1) {
                ServerModel(
//                    ID = settings.getInt("Server ID"),
                    ID = it,
                    title = settings.getString("Server Title"),
                    url = settings.getString("Server Url"),
                    token = ""
                )
            }
        )

    val servers: LiveData<List<ServerModel>> = _servers

    fun onAddPressed() {
        eventsDispatcher.dispatchEvent {
            routeToEditServer()
        }
    }

    interface EventsListener {
        fun routeToEditServer()
//        fun routeToDetails()
    }
}