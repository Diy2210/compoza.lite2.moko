package org.example.mpp

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.screen.navigation.Route
import org.example.mpp.models.NewMainViewModel
import org.example.mpp.models.EditServerViewModel
import org.example.mpp.models.ServerViewModel
import org.example.mpp.screens.DetailsScreen
import org.example.mpp.screens.EditServerScreen
import org.example.mpp.screens.NewMainScreen
import org.example.mpp.screens.ServerListScreen

class NavigationFactory(
    private val theme: Theme) {

    fun createMainScreen(routeEditServer: Route<Unit>) : NewMainScreen {
        return NewMainScreen(
            theme = theme,
            viewModelFactory = {
                NewMainViewModel(it)
            },
            routeEditServer = routeEditServer
//            routeDetails = routeDetails
        )
    }

    fun createEditServerScreen(routeToMain: Route<Unit>) : EditServerScreen {
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

    fun createDetailsScreen(routeToMain: Route<Unit>) : DetailsScreen {
        return DetailsScreen(
            theme = theme
        )
    }

    fun createServerListScreen(routeToMain: Route<Unit>) : ServerListScreen {
        return ServerListScreen(
            theme = theme,
//            viewModelFactory = {
//                ServerViewModel(it)
//            },
            viewModelFactory = {
                ServerViewModel(EventsDispatcher())
            },
            routeEditServer = routeToMain
        )
    }
}