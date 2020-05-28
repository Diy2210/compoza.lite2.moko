package org.example.mpp.models

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class ServerViewModel(
    override val eventsDispatcher: EventsDispatcher<ServerViewModel.EventsListener>
): ViewModel(), EventsDispatcherOwner<ServerViewModel.EventsListener> {

    private val _servers: MutableLiveData<List<ServerModel>> =
        MutableLiveData(
            initialValue = List(5) {
                ServerModel(
                    ID = it,
                    title = "Test",
                    url = "test.com",
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