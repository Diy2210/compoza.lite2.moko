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

    val serverTitleField = FormField<String, StringDesc>("", liveBlock { null })
    val serverUrlField = FormField<String, StringDesc>("", liveBlock { null })
    val serverTokenField = FormField<String, StringDesc>("", liveBlock { null })

    fun onSavePressed() {
        val title = serverTitleField.data.value
        val url = serverUrlField.data.value
        val token = serverTokenField.data.value
        println("server title: $title , server url: $url , server token: $token")
        eventsDispatcher.dispatchEvent {
//            routeInputCode(title, url, token)
            routeToMain()
        }
    }

    fun onCancelPressed() {
        eventsDispatcher.dispatchEvent {
            routeToMain()
        }
    }

    interface EventsListener {
//        fun routeInputCode(title: String, url: String, token: String)
        fun routeToMain()
    }
}