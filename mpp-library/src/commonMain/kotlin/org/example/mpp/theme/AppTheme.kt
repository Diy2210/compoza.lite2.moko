package org.example.mpp.theme

import dev.icerock.moko.graphics.Color
import dev.icerock.moko.widgets.factory.SystemButtonViewFactory
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.style.background.Background
import dev.icerock.moko.widgets.style.background.Fill
import dev.icerock.moko.widgets.style.state.PressableState
import dev.icerock.moko.widgets.style.view.TextStyle
import org.example.mpp.screens.screenEditServer.EditServerScreen
import org.example.mpp.screens.screenServerList.ServerListScreen

val main get() = Color(0x1BBCACFF)
val transparent get() = Color(0x00000000)
val white get() = Color(0xffffffffFF)


object AppTheme {
    val baseTheme = Theme {
        factory[ServerListScreen.Ids.CreateNewServer] = SystemButtonViewFactory(
//            icon = PressableState(all = MR.images.plus_icon_png),
            background = PressableState(
                normal = Background(
                    fill = Fill.Solid(color = main),
                    cornerRadius = 100f
                ),
                disabled = Background(
                    fill = Fill.Solid(color = main)
                ),
                pressed = Background(
                    fill = Fill.Solid(color = transparent)
                )
            ),
            textStyle  = TextStyle(
                color = PressableState(
                    normal = white,
                    pressed = main,
                    disabled = white
                ),
                size = 25
            )
        )
        factory[EditServerScreen.Ids.CancelBtn] = SystemButtonViewFactory(
            background = PressableState(
                normal = Background(
                    fill = Fill.Solid(color = transparent)
                ),
                disabled = Background(
                    fill = Fill.Solid(color = transparent)
                ),
                pressed = Background(
                    fill = Fill.Solid(color = transparent)
                )
            ),
            textStyle  = TextStyle(
                color = PressableState(
                    normal = main,
                    pressed = white,
                    disabled = main
                ),
                size = 12
            )
        )
        factory[EditServerScreen.Ids.SaveBtn] = SystemButtonViewFactory(
            background = PressableState(
                normal = Background(
                    fill = Fill.Solid(color = main),
                    cornerRadius = 5f
                ),
                disabled = Background(
                    fill = Fill.Solid(color = main)
                ),
                pressed = Background(
                    fill = Fill.Solid(color = main)
                )
            ),
            textStyle  = TextStyle(
                color = PressableState(
                    normal = white,
                    pressed = main,
                    disabled = white
                ),
                size = 12
            )
        )
    }
}