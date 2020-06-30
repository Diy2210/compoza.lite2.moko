package org.example.mpp.navigation

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.screen.navigation.Route
import kotlinx.serialization.ImplicitReflectionSerializer
import org.example.mpp.screens.screenDetails.DetailsModel
import org.example.mpp.screens.screenEditServer.EditServerViewModel
import org.example.mpp.screens.screenServerList.ServerViewModel
import org.example.mpp.screens.screenDetails.DetailsScreen
import org.example.mpp.screens.screenEditServer.EditServerScreen
import org.example.mpp.screens.screenServerList.ServerListScreen

class NavigationFactory(
    private val theme: Theme
) {

    fun createServerListScreen(routeToMain: Route<Unit>, routeToDetails: Route<Unit>): ServerListScreen {
        return ServerListScreen(
            theme = theme,
//            viewModelFactory = {
//                ServerViewModel(it)
//            },
            viewModelFactory = {
                ServerViewModel(EventsDispatcher())
            },
            routeEditServer = routeToMain,
            routeDetails = routeToDetails
        )
    }

    fun createEditServerScreen(routeToMain: Route<Unit>): EditServerScreen {
        return EditServerScreen(
            theme = theme,
            viewModelFactory = {
                EditServerViewModel(it)
            },
//            viewModelFactory = { eventsDispatcher ->
//                ServerViewModel(eventsDispatcher)
//            },
            routeToMain = routeToMain
        )
    }

    @ImplicitReflectionSerializer
    fun createDetailsScreen(routeToMain: Route<Unit>): DetailsScreen {
        return DetailsScreen(
            theme = theme,
//            resObject = ResponseModel(data = DataModel(disk_fs = , status = , host = , progs = ))
            viewModelFactory = {
                DetailsModel()
            }
        )
    }
}