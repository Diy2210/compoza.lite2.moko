package net.compoza.lite2.mpp.screens.screenServerList

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import net.compoza.lite2.ServerRepository
import net.compoza.lite2.Servers

class ServerViewModel(
    override val eventsDispatcher: EventsDispatcher<EventsListener>
) : ViewModel(), EventsDispatcherOwner<ServerViewModel.EventsListener> {

    val serverRepository: ServerRepository = ServerRepository()
    private var list: List<Servers> = emptyList()
    private val _servers: MutableLiveData<List<Servers>> = MutableLiveData(list)

    init {
        reload()
    }

    fun reload() {
        list = serverRepository.list()
        _servers.value = list
    }

    val servers: LiveData<List<Servers>> = _servers

    fun onAddPressed() {
        eventsDispatcher.dispatchEvent {
            routeToEditServer()
        }
    }

    fun onClickToItem(itemId: Long, title: String, url: String, token: String) {
        eventsDispatcher.dispatchEvent {
            routeToDetails(itemId, title, url, token)
        }
    }

    interface EventsListener {
        fun routeToEditServer()
        fun routeToDetails(itemId: Long, title: String, url: String, token: String)
    }
}