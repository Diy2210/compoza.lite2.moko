package net.compoza.lite2.mpp.screens.screenServerList

import com.russhwolf.settings.Settings
import com.russhwolf.settings.invoke
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import net.compoza.lite2.ServerRepository
import net.compoza.lite2.mpp.screens.screenServerList.ServerItem as ServerModel

class ServerViewModel(
    override val eventsDispatcher: EventsDispatcher<EventsListener>
) : ViewModel(), EventsDispatcherOwner<ServerViewModel.EventsListener> {

    private val settings: Settings = Settings()
    var id = settings.getInt("Server ID")
    var title = settings.getString("Server Title")
    var url = settings.getString("Server Url")
    var token = settings.getString("Server Token")
    private val serverRepository: ServerRepository = ServerRepository()

    private val _servers: MutableLiveData<List<ServerModel>> =
        MutableLiveData(initialValue = List(1) {
            ServerModel(
                ID = id,
                title = title,
                url = url,
                token = token
            )
        }
    )

//    private val _servers: MutableLiveData<List<ServerModel>> =
//        MutableLiveData(initialValue = List(1) {
//            serverRepository.list(
//                ID = id,
//                title = title,
//                url = url,
//                token = token
//            )
//        }
//    )

    val servers: LiveData<List<ServerModel>> = _servers

    fun onAddPressed() {
        eventsDispatcher.dispatchEvent {
            routeToEditServer()
        }
    }

    fun onClickToItem(model: ServerModel) {
        eventsDispatcher.dispatchEvent {
            routeToDetails()
        }
    }

    interface EventsListener {
        fun routeToEditServer()
        fun routeToDetails()
    }
}