package org.example.mpp.theme

import dev.icerock.moko.graphics.Color
import dev.icerock.moko.widgets.ButtonWidget
import dev.icerock.moko.widgets.TextWidget
import dev.icerock.moko.widgets.factory.SystemButtonViewFactory
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.factory.ButtonWithIconViewFactory
import dev.icerock.moko.widgets.factory.SystemTextViewFactory
import dev.icerock.moko.widgets.style.background.Background
import dev.icerock.moko.widgets.style.background.Fill
import dev.icerock.moko.widgets.style.state.PressableState
import dev.icerock.moko.widgets.style.view.*
import org.example.library.MR
import org.example.mpp.screens.screenDetails.DetailsScreen
import org.example.mpp.screens.screenEditServer.EditServerScreen

val colorAccent get() = Color(0x1BBCACFF)
val colorPrimary get() = Color(0x3155ABFF)
val colorPrimaryDark get() = Color(0x243d79FF)
val transparent get() = Color(0x00000000)
val white get() = Color(0xffffffffFF)

object AppTheme {
    object CreateNewServerBtn : ButtonWidget.Category
    object TextStyleCategory : TextWidget.Category
    object TextStyleValue : TextWidget.Category
    val baseTheme = Theme {
        // Style Text Category
        factory[TextStyleCategory] = SystemTextViewFactory (
            textStyle = TextStyle(15, color = colorPrimary, fontStyle = FontStyle.BOLD),
            margins = MarginValues(8f)
        )

        // Style Text Value
        factory[TextStyleValue] = SystemTextViewFactory (
            textStyle = TextStyle(14),
            margins = MarginValues(8f, 0f, 0f, 0f)
        )

        // Style CreateNewServerBtn
        factory[CreateNewServerBtn] = ButtonWithIconViewFactory(
            icon = PressableState(all = MR.images.plus_icon_png),
            background = PressableState(
                all = Background(
                    fill = Fill.Solid(color = colorAccent),
                    cornerRadius = 50f
                )
            ),
            textStyle = TextStyle(
                color = PressableState(
                    all = Colors.white
                ),
                size = 15
            )
        )

        // Style Cancel Button
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
                    normal = colorAccent,
                    pressed = white,
                    disabled = colorAccent
                ),
                size = 12
            )
        )

        // Style Save Button
        factory[EditServerScreen.Ids.SaveBtn] = SystemButtonViewFactory(
            background = PressableState(
                normal = Background(
                    fill = Fill.Solid(color = colorAccent),
                    cornerRadius = 5f
                ),
                disabled = Background(
                    fill = Fill.Solid(color = colorAccent)
                ),
                pressed = Background(
                    fill = Fill.Solid(color = colorAccent)
                )
            ),
            textStyle  = TextStyle(
                color = PressableState(
                    normal = white,
                    pressed = colorAccent,
                    disabled = white
                ),
                size = 12
            )
        )
    }
}