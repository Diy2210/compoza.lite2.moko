package org.example.mpp.screens

import com.russhwolf.settings.Settings
import com.russhwolf.settings.invoke
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.TextWidget
import dev.icerock.moko.widgets.constraint
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.WidgetScreen
import dev.icerock.moko.widgets.screen.navigation.NavigationBar
import dev.icerock.moko.widgets.screen.navigation.NavigationItem
import dev.icerock.moko.widgets.style.view.WidgetSize
import dev.icerock.moko.widgets.text
import org.example.library.MR

class DetailsScreen(
    private val theme: Theme
) : WidgetScreen<Args.Empty>(), NavigationItem {

    override val navigationBar: NavigationBar = NavigationBar.Normal(MR.strings.compoza_lite.desc())
    val settings: Settings = Settings()

    override fun createContentWidget() = with(theme) {
        constraint(size = WidgetSize.AsParent) {
            val serverName = +text(
                id = Ids.ServerName,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const(settings.getString("Server Title"))
            )
            val hostnameLabel = +text(
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const("System Hostmane")
            )
            val url = +text(
                id = Ids.Url,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const(settings.getString("Server Url"))
            )
            val systemLabel = +text(
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const("System OS")
            )
            val system = +text(
                id = Ids.System,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const("-")
            )
            val ipLabel = +text(
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const("Public IP")
            )
            val ip = +text(
                id = Ids.IP,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const("-")
            )
            val cpuLabel = +text(
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const("Kernel and CPU")
            )
            val cpu = +text(
                id = Ids.CPU,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const("-")
            )
            val systemUptimeLabel = +text(
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const("System Uptime")
            )
            val systemUptime = +text(
                id = Ids.SystemUptime,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const("-")
            )
            val systemDateLabel = +text(
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const("System Date")
            )
            val systemDate = +text(
                id = Ids.SystemDate,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const("-")
            )

            constraints {
                serverName topToTop root offset 8
                serverName leftToLeft root offset 16

                hostnameLabel topToBottom  serverName offset 32
                hostnameLabel leftRightToLeftRight root offset 16

                url topToBottom hostnameLabel offset 0
                url leftRightToLeftRight root offset 16

                systemLabel topToBottom url offset 16
                systemLabel leftRightToLeftRight root offset 16

                system topToBottom systemLabel offset 0
                system leftRightToLeftRight root offset 16

                ipLabel topToBottom system offset 16
                ipLabel leftRightToLeftRight root offset 16

                ip topToBottom ipLabel offset 0
                ip leftRightToLeftRight root offset 16

                cpuLabel topToBottom ip offset 16
                cpuLabel leftRightToLeftRight root offset 16

                cpu topToBottom cpuLabel offset 0
                cpu leftRightToLeftRight root offset 16

                systemUptimeLabel topToBottom cpu offset 16
                systemUptimeLabel leftRightToLeftRight root offset 16

                systemUptime topToBottom systemUptimeLabel offset 0
                systemUptime leftRightToLeftRight root offset 16

                systemDateLabel topToBottom systemUptime offset 16
                systemDateLabel leftRightToLeftRight root offset 16

                systemDate topToBottom systemDateLabel offset 0
                systemDate leftRightToLeftRight root offset 16
            }
        }
    }

    object Ids {
        object ServerName : TextWidget.Id
        object Url : TextWidget.Id
        object System : TextWidget.Id
        object IP : TextWidget.Id
        object CPU : TextWidget.Id
        object SystemUptime : TextWidget.Id
        object SystemDate : TextWidget.Id
    }
}