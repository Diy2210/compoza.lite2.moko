package org.example.mpp.models

import dev.icerock.moko.fields.FormField
import dev.icerock.moko.fields.liveBlock
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import org.example.mpp.api.CompozaApi

class EditServerViewModel(
    override val eventsDispatcher: EventsDispatcher<EventsListener>
) : ViewModel(), EventsDispatcherOwner<EditServerViewModel.EventsListener> {

//    var r: String = ""
//    val c = CompozaApi()

    val serverTitleField = FormField<String, StringDesc>("", liveBlock { null })
    val serverUrlField = FormField<String, StringDesc>("", liveBlock { null })
    val serverTokenField = FormField<String, StringDesc>("", liveBlock { null })

    fun onSavePressed() {
        val title = serverTitleField.data.value
        val url = serverUrlField.data.value
        val token = serverTokenField.data.value

        if (title.isBlank() || url.isBlank() || token.isBlank()) {
            eventsDispatcher.dispatchEvent {
                showError("All fields are required".desc())
            }
            return
        }

        eventsDispatcher.dispatchEvent {
//            routeInputCode(title, url, token)
            println("server title: $title , server url: $url , server token: $token")

//            try {
//                c.getStatusServer(url, title, token).also { response ->
//                    r = response
//                    println(r.desc())
//                }
//            } catch (e: Exception) {
//                println(r.desc())
//            }

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
        fun showError(error: StringDesc)
    }
}