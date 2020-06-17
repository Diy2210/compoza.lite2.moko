package org.example.mpp

import dev.icerock.moko.graphics.Color
import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.BaseApplication
import dev.icerock.moko.widgets.screen.ScreenDesc
import dev.icerock.moko.widgets.screen.TypedScreenDesc
import dev.icerock.moko.widgets.screen.navigation.*
import org.example.mpp.theme.AppTheme
import org.example.mpp.navigation.NavigationFactory
import org.example.mpp.screens.DetailsScreen
import org.example.mpp.screens.EditServerScreen
import org.example.mpp.screens.ServerListScreen

val main get() = Color(0x1BBCACFF)
val transparent get() = Color(0x00000000)
val white get() = Color(0xffffffffFF)

class App : BaseApplication() {
    override fun setup(): ScreenDesc<Args.Empty> {
//        val theme = AppTheme() {
//            factory[ServerListScreen.Ids.CreateNewServer] = SystemButtonViewFactory(
//                background = PressableState(
//                    normal = Background(
//                        fill = Fill.Solid(color = main),
//                        cornerRadius = 100f
//                    ),
//                    disabled = Background(
//                        fill = Fill.Solid(color = main)
//                    ),
//                    pressed = Background(
//                        fill = Fill.Solid(color = main)
//                    )
//                ),
//                textStyle  = TextStyle(
//                    color = PressableState(
//                        normal = white,
//                        pressed = main,
//                        disabled = white
//                    )
//                )
//            )
//            factory[EditServerScreen.Ids.CancelBtn] = SystemButtonViewFactory(
//                background = PressableState(
//                    normal = Background(
//                        fill = Fill.Solid(color = transparent)
//                    ),
//                    disabled = Background(
//                        fill = Fill.Solid(color = transparent)
//                    ),
//                    pressed = Background(
//                        fill = Fill.Solid(color = transparent)
//                    )
//                ),
//                textStyle  = TextStyle(
//                    color = PressableState(
//                        normal = main,
//                        pressed = white,
//                        disabled = main
//                    )
//                )
//            )
//            factory[EditServerScreen.Ids.SaveBtn] = SystemButtonViewFactory(
//                background = PressableState(
//                    normal = Background(
//                        fill = Fill.Solid(color = main)
//                    ),
//                    disabled = Background(
//                        fill = Fill.Solid(color = main)
//                    ),
//                    pressed = Background(
//                        fill = Fill.Solid(color = main)
//                    )
//                )
//            )
//        }

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

