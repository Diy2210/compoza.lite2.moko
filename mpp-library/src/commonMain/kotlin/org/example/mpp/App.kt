package org.example.mpp

import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.BaseApplication
import dev.icerock.moko.widgets.screen.ScreenDesc
import dev.icerock.moko.widgets.screen.TypedScreenDesc
import dev.icerock.moko.widgets.screen.navigation.*
import org.example.mpp.navigation.NavigationFactory
import org.example.mpp.screens.DetailsScreen
import org.example.mpp.screens.EditServerScreen
import org.example.mpp.screens.ServerListScreen

class App : BaseApplication() {
    override fun setup(): ScreenDesc<Args.Empty> {
        val theme = Theme()

        val navigationFactory = NavigationFactory(theme)

        return registerScreen(RootNavigationScreen::class) {
            val rootNavigationRouter = createRouter()

            val mainScreen = registerScreen(MainScreen::class) {
                MainScreen(theme)
            }

            val detailsScreen = registerScreen(DetailsScreen::class) {
                navigationFactory.createDetailsScreen(
                    routeToMain = rootNavigationRouter.createPopToRootRoute()
                )
            }

            val editServerScreen = registerScreen(EditServerScreen::class) {
                navigationFactory.createEditServerScreen(
                    routeToMain = rootNavigationRouter.createPopToRootRoute()
                )
            }

            val serverListScreen = registerScreen(ServerListScreen::class) {
                navigationFactory.createServerListScreen(
                    routeToMain = rootNavigationRouter.createPushRoute(editServerScreen)
                )
            }

            RootNavigationScreen(
                initialScreen = serverListScreen,
                router = rootNavigationRouter
            )
        }
    }

    class RootNavigationScreen(
        initialScreen: TypedScreenDesc<Args.Empty, ServerListScreen>,
        router: Router
    ) : NavigationScreen<ServerListScreen>(initialScreen, router)
}

