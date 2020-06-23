package org.example.mpp.screens.screenDetails

import com.russhwolf.settings.Settings
import com.russhwolf.settings.invoke
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.*
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.WidgetScreen
import dev.icerock.moko.widgets.screen.navigation.NavigationBar
import dev.icerock.moko.widgets.screen.navigation.NavigationItem
import dev.icerock.moko.widgets.style.view.FontStyle
import dev.icerock.moko.widgets.style.view.TextStyle
import dev.icerock.moko.widgets.style.view.WidgetSize
import org.example.library.MR
import org.example.mpp.theme.AppTheme

class DetailsScreen(
    private val theme: Theme
) : WidgetScreen<Args.Empty>(), NavigationItem {

    override val navigationBar
        get() = NavigationBar.Normal(
            title = MR.strings.compoza_lite.desc(),
            styles = NavigationBar.Styles(
                backgroundColor = Color(0x3155ABFF),
                tintColor = Color(0xffffffffFF),
                textStyle = TextStyle(
                    color = Color(0xffffffffFF),
                    size = 18,
                    fontStyle = FontStyle.BOLD
                )
            )
        )

    private val settings: Settings = Settings()

    override fun createContentWidget() = with(theme) {
        scroll(
            id = Ids.RootScroll,
            size = WidgetSize.AsParent,
            child = linear(size = WidgetSize.WidthAsParentHeightWrapContent) {
                +text(
                    id = Ids.ServerName,
                    category = AppTheme.TextStyleCategory,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const(settings.getString("Server Title"))
                )
                +text(
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const("System Hostmane")
                )
                +text(
                    id = Ids.Url,
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const(settings.getString("hostname"))
                )
                +text(
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const("System OS")
                )
                +text(
                    id = Ids.System,
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const(settings.getString("os"))
                )
                +text(
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const("Public IP")
                )
                +text(
                    id = Ids.IP,
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const(settings.getString("ip"))
                )
                +text(
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const("Kernel and CPU")
                )
                +text(
                    id = Ids.CPU,
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const(settings.getString("kernel"))
                )
                +text(
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const("System Uptime")
                )
                +text(
                    id = Ids.SystemUptime,
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const(settings.getString("uptime"))
                )
                +text(
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const("System Date")
                )
                +text(
                    id = Ids.SystemDate,
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const(settings.getString("date"))
                )
                +text(
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const("Updates")
                )
                +text(
                    id = Ids.Updates,
                    category = AppTheme.TextStyleValue,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const("Available: " + settings.getInt("updates").toString())
                )
                +text(
                    category = AppTheme.TextStyleCategory,
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const("Disk Usage")
                )

            }
        )
    }

    object Ids {
        object RootScroll : ScrollWidget.Id
        object ServerName : TextWidget.Id
        object Url : TextWidget.Id
        object System : TextWidget.Id
        object IP : TextWidget.Id
        object CPU : TextWidget.Id
        object SystemUptime : TextWidget.Id
        object SystemDate : TextWidget.Id
        object Updates : TextWidget.Id
    }
}
