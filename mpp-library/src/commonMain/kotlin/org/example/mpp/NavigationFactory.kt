package org.example.mpp

import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.screen.navigation.Route
import org.example.mpp.models.NewMainViewModel
import org.example.mpp.models.ServerViewModel
import org.example.mpp.screens.EditServerScreen
import org.example.mpp.screens.NewMainScreen

class NavigationFactory(
    private val theme: Theme) {

    fun createMainScreen(routeEditServer: Route<Unit>, routeDetails: Route<Unit>): NewMainScreen {
        return NewMainScreen(
            theme = theme,
            viewModelFactory = {
                NewMainViewModel(it)
            },
            routeEditServer = routeEditServer,
            routeDetails = routeDetails
        )
    }

    fun createEditServerScreen(routeToMain: Route<Unit>): EditServerScreen {
        return EditServerScreen(
            theme = theme,
            viewModelFactory = {
                ServerViewModel(it)
            },
//            viewModelFactory = { eventsDispatcher ->
//                ServerViewModel(eventsDispatcher)
//            },
            routeToMain = routeToMain
        )
    }
}