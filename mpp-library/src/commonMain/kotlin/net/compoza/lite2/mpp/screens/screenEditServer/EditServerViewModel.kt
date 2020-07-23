package net.compoza.lite2.mpp.screens.screenEditServer

import dev.icerock.moko.fields.FormField
import dev.icerock.moko.fields.liveBlock
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import kotlinx.coroutines.launch
import net.compoza.lite2.*

class EditServerViewModel(
    override val eventsDispatcher: EventsDispatcher<EventsListener>
) : ViewModel(), EventsDispatcherOwner<EditServerViewModel.EventsListener> {

    private val serverRepository: ServerRepository = ServerRepository()

    val serverTitleField = FormField<String, StringDesc>("", liveBlock { null })
    val serverUrlField = FormField<String, StringDesc>("", liveBlock { null })
    val serverTokenField = FormField<String, StringDesc>("", liveBlock { null })

    fun onSavePressed() {
        val server = Servers(0, serverTitleField.data.value, serverUrlField.data.value, serverTokenField.data.value)

        if (server.title.isEmpty() || server.url.isEmpty() || server.token.isEmpty()) {
            eventsDispatcher.dispatchEvent {
                showError("All fields are required".desc())
            }
            return
        }

        viewModelScope.launch {
            try {
                // Insert New Server
                serverRepository.insert(server)
                println("Complete!")
                println(serverRepository.list())
                eventsDispatcher.dispatchEvent {
                    routeToMain()
                }
                // Update Server

            } catch (e: Exception) {
                println("Error Insert Server")
            }
        }
    }

    fun onCancelPressed() {
        eventsDispatcher.dispatchEvent {
            routeToMain()
        }
    }

    interface EventsListener {
        fun routeToMain()
        fun showError(error: StringDesc)
    }
}