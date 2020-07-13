package net.compoza.lite2.mpp.navigation

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.screen.navigation.Route
import kotlinx.serialization.ImplicitReflectionSerializer
import net.compoza.lite2.mpp.screens.screenDetails.DetailsViewModel
import net.compoza.lite2.mpp.screens.screenEditServer.EditServerViewModel
import net.compoza.lite2.mpp.screens.screenServerList.ServerViewModel
import net.compoza.lite2.mpp.screens.screenDetails.DetailsScreen
import net.compoza.lite2.mpp.screens.screenEditServer.EditServerScreen
import net.compoza.lite2.mpp.screens.screenServerList.ServerListScreen

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
                DetailsViewModel()
            }
        )
    }
}