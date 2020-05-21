//package org.example.mpp
//
//import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
//import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
//import dev.icerock.moko.mvvm.viewmodel.ViewModel
//
//class MainViewModel(
//    override val eventsDispatcher: EventsDispatcher<EventsListener>
//) : ViewModel(), EventsDispatcherOwner<MainViewModel.EventsListener> {
//
//    fun onAddPressed() {
//        eventsDispatcher.dispatchEvent { routeToEditServer() }
//    }
//
//    interface EventsListener {
//        fun routeToEditServer()
//    }
//}