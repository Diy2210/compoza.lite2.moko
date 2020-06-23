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
import dev.icerock.moko.widgets.style.background.Orientation
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
            child = linear(
                id = Ids.RootLinearId,
                size = WidgetSize.WidthAsParentHeightWrapContent
            ) {

                // Host Linear
                +linear(
                    id = Ids.HostLinearId,
                    size = WidgetSize.WrapContent
                ) {
                    +text(
                        id = Ids.ServerName,
                        category = AppTheme.TextStyleCategory,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const(settings.getString("Server Title"))
                    )

                    // Host Info Content
                    +text(
                        category = AppTheme.TextStyleHostTitle,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("System Hostmane")
                    )
                    +text(
                        id = Ids.Url,
                        category = AppTheme.TextStyleHostValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const(settings.getString("hostname"))
                    )
                    +text(
                        category = AppTheme.TextStyleHostTitle,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("System OS")
                    )
                    +text(
                        id = Ids.System,
                        category = AppTheme.TextStyleHostValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const(settings.getString("os"))
                    )
                    +text(
                        category = AppTheme.TextStyleHostTitle,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("Public IP")
                    )
                    +text(
                        id = Ids.IP,
                        category = AppTheme.TextStyleHostValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const(settings.getString("ip"))
                    )
                    +text(
                        category = AppTheme.TextStyleHostTitle,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("Kernel and CPU")
                    )
                    +text(
                        id = Ids.CPU,
                        category = AppTheme.TextStyleHostValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const(settings.getString("kernel"))
                    )
                    +text(
                        category = AppTheme.TextStyleHostTitle,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("System Uptime")
                    )
                    +text(
                        id = Ids.SystemUptime,
                        category = AppTheme.TextStyleHostValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const(settings.getString("uptime"))
                    )
                    +text(
                        category = AppTheme.TextStyleHostTitle,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("System Date")
                    )
                    +text(
                        id = Ids.SystemDate,
                        category = AppTheme.TextStyleHostValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const(settings.getString("date"))
                    )
                    +text(
                        category = AppTheme.TextStyleHostTitle,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("Updates")
                    )
                    +text(
                        id = Ids.Updates,
                        category = AppTheme.TextStyleHostValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("Available: " + settings.getInt("updates").toString())
                    )
                }

                // Disk Linear
                +linear(
                    id = Ids.DiskUsageLinearId,
                    size = WidgetSize.WrapContent
                ) {

                    // Disk Info Content
                    +constraint(size = WidgetSize.WrapContent) {
                        val mountPoint = +text(
                            category = AppTheme.TextStyleDefaultValue,
                            size = WidgetSize.WidthAsParentHeightWrapContent,
                            text = const(settings.getString("Mount Point"))
                        )
                        val free = +text(
                            category = AppTheme.TextStyleDefaultValue,
                            size = WidgetSize.WidthAsParentHeightWrapContent,
                            text = const(settings.getString("Free"))
                        )
                        val total = +text(
                            category = AppTheme.TextStyleDefaultValue,
                            size = WidgetSize.WidthAsParentHeightWrapContent,
                            text = const(settings.getString("Total"))
                        )

                        constraints {
                            mountPoint topToBottom root offset 8
                            mountPoint leftToLeft root offset 16

                            free topToTop root offset 8
                            free rightToRight root offset 16

                            total topToTop root offset 8
                            total rightToRight root offset 16

                        }
                    }
                }

                // Server Status Linear
                +linear(
                    id = Ids.ServerStatusLinearId,
                    size = WidgetSize.WrapContent
                ) {

                    // Server Status Content
                    +text(
                        category = AppTheme.TextStyleCategory,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("Server Status")
                    )
                    +text(
                        category = AppTheme.TextStyleDefaultValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("id")
                    )
                    +text(
                        category = AppTheme.TextStyleDefaultValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("-")
                    )
                    +text(
                        category = AppTheme.TextStyleDefaultValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("Name")
                    )
                    +text(
                        category = AppTheme.TextStyleDefaultValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("-")
                    )
                    +text(
                        category = AppTheme.TextStyleDefaultValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("Status")
                    )
                    +text(
                        category = AppTheme.TextStyleDefaultValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("-")
                    )
                    +text(
                        category = AppTheme.TextStyleDefaultValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("Future")
                    )
                    +text(
                        category = AppTheme.TextStyleDefaultValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("-")
                    )
                }

                // Software Linear
                +linear(
                    id = Ids.SoftwareInfoLinearId,
                    size = WidgetSize.WrapContent
                ) {

                    // Software Versions Content
                    +text(
                        category = AppTheme.TextStyleCategory,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("Software Versions")
                    )
                    +text(
                        category = AppTheme.TextStyleDefaultValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("id")
                    )
                    +text(
                        category = AppTheme.TextStyleDefaultValue,
                        size = WidgetSize.WidthAsParentHeightWrapContent,
                        text = const("-")
                    )
                }

                // Disk Usage
//                val diskUsage = +text(
//                    category = AppTheme.TextStyleCategory,
//                    size = WidgetSize.WidthAsParentHeightWrapContent,
//                    text = const("Disk Usage")
//                )
//                +text(
//                    category = AppTheme.TextStyleDefaultValue,
//                    size = WidgetSize.WidthAsParentHeightWrapContent,
//                    text = const("Mount Point")
//                )
//                +text(
//                    category = AppTheme.TextStyleDefaultValue,
//                    size = WidgetSize.WidthAsParentHeightWrapContent,
//                    text = const("/ Directory")
//                )
//                +text(
//                    category = AppTheme.TextStyleDefaultValue,
//                    size = WidgetSize.WidthAsParentHeightWrapContent,
//                    text = const("Free")
//                )
//                +text(
//                    category = AppTheme.TextStyleDefaultValue,
//                    size = WidgetSize.WidthAsParentHeightWrapContent,
//                    text = const("-")
//                )
//                +text(
//                    category = AppTheme.TextStyleDefaultValue,
//                    size = WidgetSize.WidthAsParentHeightWrapContent,
//                    text = const("Total")
//                )
//                +text(
//                    category = AppTheme.TextStyleDefaultValue,
//                    size = WidgetSize.WidthAsParentHeightWrapContent,
//                    text = const("-")
//                )

            }
        )
    }

    object Ids {
        object RootLinearId : LinearWidget.Id
        object HostLinearId : LinearWidget.Id
        object DiskUsageLinearId : LinearWidget.Id
        object ServerStatusLinearId : LinearWidget.Id
        object SoftwareInfoLinearId : LinearWidget.Id
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
