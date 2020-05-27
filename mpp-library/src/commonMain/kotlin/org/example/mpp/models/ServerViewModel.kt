package org.example.mpp.models

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class ServerViewModel(
    override val eventsDispatcher: EventsDispatcher<ServerViewModel.EventsListener>
): ViewModel(), EventsDispatcherOwner<ServerViewModel.EventsListener> {

    private val _servers: MutableLiveData<List<Server>> =
        MutableLiveData(
            initialValue = List(5) {
                Server(
                    id = it,
                    title = "Test",
                    url = "test.com"
                )
            }
        )

    val servers: LiveData<List<Server>> = _servers

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