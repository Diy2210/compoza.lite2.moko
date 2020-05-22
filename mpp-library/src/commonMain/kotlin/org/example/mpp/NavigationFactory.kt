package org.example.mpp

import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.screen.navigation.Route

class NavigationFactory(private val theme: Theme) {
    fun createMainScreen(routeEditServer: Route<Unit>): NewMainScreen {
        return NewMainScreen(
            theme = theme,
            viewModelFactory = {
                NewMainViewModel(it)
            },
            routeEditServer = routeEditServer
        )
    }

    fun createEditServerScreen(routeToMain: Route<Unit>): EditServerScreen {
        return EditServerScreen(
            theme = theme,
            viewModelFactory = {
                ServerViewModel(it)
            },
            routeToMain = routeToMain
        )
    }
}