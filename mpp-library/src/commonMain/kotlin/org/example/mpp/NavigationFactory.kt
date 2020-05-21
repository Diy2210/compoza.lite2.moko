package org.example.mpp

import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.screen.navigation.Route

class NavigationFactory(private val theme: Theme
) {
    fun createMainScreenScreen(routeNewMain: Route<Unit>): NewMainScreen {
        return NewMainScreen(
            theme = theme,
            viewModelFactory = {
                NewMainViewModel(it)
            },
            routeNewMain = routeNewMain
        )
    }

    fun createEditServerScreen(editServerRoute: Route<Unit>): EditServerScreen {
        return EditServerScreen(
            theme = theme,
            viewModelFactory = {
                ServerViewModel(it)
            },
            editServerRoute = editServerRoute
        )
    }
}