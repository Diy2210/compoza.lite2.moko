package net.compoza.lite2.mpp

import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.BaseApplication
import dev.icerock.moko.widgets.screen.ScreenDesc
import dev.icerock.moko.widgets.screen.TypedScreenDesc
import dev.icerock.moko.widgets.screen.navigation.*
import kotlinx.serialization.ImplicitReflectionSerializer
import net.compoza.lite2.mpp.theme.AppTheme
import net.compoza.lite2.mpp.navigation.NavigationFactory
import net.compoza.lite2.mpp.screens.screenDetails.DetailsScreen
import net.compoza.lite2.mpp.screens.screenEditServer.EditServerScreen
import net.compoza.lite2.mpp.screens.screenServerList.ServerListScreen

class App : BaseApplication() {
    @ImplicitReflectionSerializer
    override fun setup(): ScreenDesc<Args.Empty> {
        val navigationFactory = NavigationFactory(AppTheme.baseTheme)

        return registerScreen(RootNavigationScreen::class) {
            val rootNavigationRouter = createRouter()

//            val mainScreen = registerScreen(MainScreen::class) {
//                MainScreen(theme)
//            }

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
                    routeToMain = rootNavigationRouter.createPushRoute(editServerScreen),
                    routeToDetails = rootNavigationRouter.createPushRoute(detailsScreen)
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

