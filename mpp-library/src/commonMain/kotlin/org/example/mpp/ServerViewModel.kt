package org.example.mpp

import dev.icerock.moko.fields.FormField
import dev.icerock.moko.fields.liveBlock
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc

class ServerViewModel(
    override val eventsDispatcher: EventsDispatcher<EventsListener>
) : ViewModel(), EventsDispatcherOwner<ServerViewModel.EventsListener> {

    val serverTitle = FormField<String, StringDesc>("", liveBlock { null })
    val serverUrl = FormField<String, StringDesc>("", liveBlock { null })
    val serverToken = FormField<String, StringDesc>("", liveBlock { null })

    val serverTitleField: FormField<String, StringDesc> = FormField(
        initialValue = "",
        validation = liveBlock { null }
    )
    val serverUrlField: FormField<String, StringDesc> = FormField(
        initialValue = "",
        validation = liveBlock { null }
    )
    val serverTokenField: FormField<String, StringDesc> = FormField(
        initialValue = "",
        validation = liveBlock { null }
    )

    fun onSavePressed() {
        val title = serverTitleField.data.value
        val url = serverTitleField.data.value
        val token = serverTitleField.data.value
        println("server title: $title " + "server url: $url" + "token: $token")
        eventsDispatcher.dispatchEvent {
            routeInputCode(title, url, token)
        }
    }

    fun onCancelPressed() {
        eventsDispatcher.dispatchEvent { routeToMain() }
    }

    interface EventsListener {
        fun routeInputCode(title: String, url: String, token: String)
        fun routeToMain()
    }
}