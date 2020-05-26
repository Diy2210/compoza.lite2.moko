package org.example.mpp.models

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class NewMainViewModel(
    override val eventsDispatcher: EventsDispatcher<EventsListener>
) : ViewModel(), EventsDispatcherOwner<NewMainViewModel.EventsListener> {

    fun onAddPressed() {
        eventsDispatcher.dispatchEvent {
            routeToEditServer()
        }
    }

    fun onDetailsPressed() {
        eventsDispatcher.dispatchEvent {
            routeToDetails()
        }
    }

    interface EventsListener {
        fun routeToEditServer()
        fun routeToDetails()
    }
}