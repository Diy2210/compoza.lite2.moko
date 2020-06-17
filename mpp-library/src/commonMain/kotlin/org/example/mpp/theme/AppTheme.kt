package org.example.mpp.theme

import dev.icerock.moko.widgets.factory.SystemButtonViewFactory
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.style.background.Background
import dev.icerock.moko.widgets.style.background.Fill
import dev.icerock.moko.widgets.style.state.PressableState
import dev.icerock.moko.widgets.style.view.TextStyle
import org.example.mpp.main
import org.example.mpp.screens.EditServerScreen
import org.example.mpp.screens.ServerListScreen
import org.example.mpp.transparent
import org.example.mpp.white

object AppTheme {
    val baseTheme = Theme {
        factory[ServerListScreen.Ids.CreateNewServer] = SystemButtonViewFactory(
            background = PressableState(
                normal = Background(
                    fill = Fill.Solid(color = main),
                    cornerRadius = 100f
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
                )
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
                )
            )
        )
        factory[EditServerScreen.Ids.SaveBtn] = SystemButtonViewFactory(
            background = PressableState(
                normal = Background(
                    fill = Fill.Solid(color = main)
                ),
                disabled = Background(
                    fill = Fill.Solid(color = main)
                ),
                pressed = Background(
                    fill = Fill.Solid(color = main)
                )
            )
        )
    }
}