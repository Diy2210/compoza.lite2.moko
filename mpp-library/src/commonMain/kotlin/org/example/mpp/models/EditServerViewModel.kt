package org.example.mpp.models

import com.russhwolf.settings.*
import dev.icerock.moko.fields.FormField
import dev.icerock.moko.fields.liveBlock
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc

class EditServerViewModel(
    override val eventsDispatcher: EventsDispatcher<EventsListener>
) : ViewModel(), EventsDispatcherOwner<EditServerViewModel.EventsListener> {

//    val c = CompozaApi()
    val settings: Settings = Settings()

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
//            try {
//                c.getStatusServer(url, title, token).also { response ->
//                    r = response
//                    println(r.desc())
//                }
//            } catch (e: Exception) {
//                println(r.desc())
//            }

//            println("server title: $title , server url: $url , server token: $token")
//            settings["Server"] = "$title $url $token"

//            val a: String by settings.string(defaultValue = token)
//            println("///////////////////////////////" + a)
//            println(settings.getString("Server"))

//            val keys: Set<String> = settings.keys
//            println(keys)
//
            settings.putInt("Server ID", 0)
            settings.putString("Server Title", title)
            settings.putString("Server Url", url)
            settings.putString("Server Token", token)
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