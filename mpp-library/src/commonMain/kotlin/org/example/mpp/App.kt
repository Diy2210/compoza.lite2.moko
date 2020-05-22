package org.example.mpp

import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.BaseApplication
import dev.icerock.moko.widgets.screen.ScreenDesc
import dev.icerock.moko.widgets.screen.TypedScreenDesc
import dev.icerock.moko.widgets.screen.navigation.NavigationScreen
import dev.icerock.moko.widgets.screen.navigation.createPushRoute
import dev.icerock.moko.widgets.screen.navigation.createReplaceRoute
import dev.icerock.moko.widgets.screen.navigation.createRouter

class App : BaseApplication() {
    override fun setup(): ScreenDesc<Args.Empty> {
        val theme = Theme()

        val navigationFactory = NavigationFactory(theme)

        return registerScreen(RootNavigationScreen::class) {
            val rootNavigationRouter = createRouter()

            val mainScreen = registerScreen(MainScreen::class) {
                MainScreen(theme)
            }

            val editServerScreen = registerScreen(EditServerScreen::class) {
                navigationFactory.createEditServerScreen(
                    routeToMain = rootNavigationRouter.createReplaceRoute(mainScreen)
                )
            }

            val newMainScreen = registerScreen(NewMainScreen::class) {
                navigationFactory.createMainScreen(
                    routeEditServer = rootNavigationRouter.createPushRoute(editServerScreen)
                )
            }

            RootNavigationScreen(
                initialScreen = newMainScreen,
                router = rootNavigationRouter
            )
        }
    }

    class RootNavigationScreen(
        initialScreen: TypedScreenDesc<Args.Empty, NewMainScreen>,
        router: Router
    ) : NavigationScreen<NewMainScreen>(initialScreen, router)
}

