package net.compoza.lite2.mpp.theme

import dev.icerock.moko.graphics.Color
import dev.icerock.moko.widgets.ButtonWidget
import dev.icerock.moko.widgets.TextWidget
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.factory.*
import dev.icerock.moko.widgets.style.background.Background
import dev.icerock.moko.widgets.style.background.Fill
import dev.icerock.moko.widgets.style.state.PressableState
import dev.icerock.moko.widgets.style.view.*
import net.compoza.lite2.library.MR
import net.compoza.lite2.mpp.screens.screenEditServer.EditServerScreen

val colorAccent get() = Color(0x1BBCACFF)
val colorPrimary get() = Color(0x3155ABFF)
val colorPrimaryDark get() = Color(0x243d79FF)
val transparent get() = Color(0x00000000)
val white get() = Color(0xffffffffFF)
val black get() = Color(0xff000000FF)
val green get() = Color(0xff008000FF)
val red get() = Color(0xffFF0000FF)

object AppTheme {
    object MenuBtn : ButtonWidget.Category
    object PlusBtn : ButtonWidget.Category
    object CancelBtn : ButtonWidget.Category
    object SaveBtn : ButtonWidget.Category
    object TextStyleCategory : TextWidget.Category
    object TextStyleHostTitle : TextWidget.Category
    object TextStyleHostValue : TextWidget.Category
    object TextStyleDefaultValue : TextWidget.Category
    object TextStyleBoldValue : TextWidget.Category
    object TextStyleImageValue : TextWidget.Category
    object TextStyleTickSymbolValue : TextWidget.Category
    object TextStyleEmptySymbolValue : TextWidget.Category
    val baseTheme = Theme {

        // Style Text Category
        factory[TextStyleCategory] = SystemTextViewFactory (
            textStyle = TextStyle(15, color = colorPrimary, fontStyle = FontStyle.BOLD),
            margins = MarginValues(8f)
        )

        // Style Text Value Title
        factory[TextStyleHostTitle] = SystemTextViewFactory (
            textStyle = TextStyle(14, fontStyle = FontStyle.BOLD),
            margins = MarginValues(8f, 0f, 0f, 0f)
        )

        // Style Text Value Host
        factory[TextStyleHostValue] = SystemTextViewFactory (
            textStyle = TextStyle(12),
            margins = MarginValues(8f, 0f, 0f, 0f)
        )

        // Style Text Value Default
        factory[TextStyleDefaultValue] = SystemTextViewFactory (
            textStyle = TextStyle(11),
            margins = MarginValues(8f, 0f, 0f, 0f)
        )

        // Style Text Value Bold
        factory[TextStyleBoldValue] = SystemTextViewFactory (
            textStyle = TextStyle(
                11,
                fontStyle = FontStyle.BOLD
            ),
            margins = MarginValues(8f, 0f, 0f, 0f)
        )

        // Style Image Text
        factory[TextStyleImageValue] = SystemTextViewFactory (
            textStyle = TextStyle(
                15,
                fontStyle = FontStyle.BOLD
            ),
            margins = MarginValues(8f, 0f, 0f, 0f)
        )

        // Style Tick Symbol
        factory[TextStyleTickSymbolValue] = SystemTextViewFactory (
            textStyle = TextStyle(
                15,
                fontStyle = FontStyle.BOLD,
                color = green
            ),
            margins = MarginValues(8f, 0f, 0f, 0f)
        )

        // Style Empty Symbol
        factory[TextStyleEmptySymbolValue] = SystemTextViewFactory (
            textStyle = TextStyle(
                15,
                fontStyle = FontStyle.BOLD,
                color = red
            ),
            margins = MarginValues(8f, 0f, 0f, 0f)
        )

        // Style Menu Button
        factory[MenuBtn] = SystemButtonViewFactory(
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
                    normal = black,
                    pressed = white,
                    disabled = colorAccent
                ),
                fontStyle = FontStyle.BOLD,
                size = 15
            )
        )

        // Style CreateNewServerBtn with image
//        factory[CreateNewServerBtn] = ButtonWithIconViewFactory(
//            icon = PressableState(all = MR.images.plus_icon_png),
//            background = PressableState(
//                all = Background(
//                    fill = Fill.Solid(color = colorAccent),
//                    cornerRadius = 50f
//                )
//            ),
//            textStyle = TextStyle(
//                color = PressableState(
//                    all = Colors.white
//                ),
//                size = 15
//            )
//        )

        // Style CreateNewServerBtn with text
        factory[PlusBtn] = SystemButtonViewFactory(
            background = PressableState(
                all = Background(
                    fill = Fill.Solid(color = colorAccent),
                    cornerRadius = 25f
                )
            ),
            textStyle = TextStyle(
                color = PressableState(
                    all = Colors.white
                ),
                size = 22
            )
        )

        // Style Cancel Button
        factory[CancelBtn] = SystemButtonViewFactory(
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
        factory[SaveBtn] = SystemButtonViewFactory(
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
            ),
            margins = MarginValues(8f, 0f, 8f, 0f)
        )
    }
}