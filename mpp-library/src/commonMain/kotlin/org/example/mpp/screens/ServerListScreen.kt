package org.example.mpp.screens

import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.units.TableUnitItem
import dev.icerock.moko.widgets.ListWidget
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.core.Widget
import dev.icerock.moko.widgets.list
import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.WidgetScreen
import dev.icerock.moko.widgets.screen.getViewModel
import dev.icerock.moko.widgets.screen.navigation.NavigationBar
import dev.icerock.moko.widgets.screen.navigation.NavigationItem
import dev.icerock.moko.widgets.style.view.SizeSpec
import dev.icerock.moko.widgets.style.view.WidgetSize
import org.example.mpp.Server
import org.example.mpp.ServerUnitItem
import org.example.mpp.models.ServerViewModel

class ServerListScreen(
    private val theme: Theme
) : WidgetScreen<Args.Empty>(), NavigationItem {

    override val navigationBar: NavigationBar = NavigationBar.Normal(title = "Compoza Lite".desc())

    override fun createContentWidget(): Widget<WidgetSize.Const<SizeSpec.AsParent, SizeSpec.AsParent>> {
        val viewModel = getViewModel {
            ServerViewModel()
        }

        return with(theme) {
            list(
                size = WidgetSize.AsParent,
                id = Ids.List,
                items = viewModel.servers.map {
                    serversToTableUnits(it)
                }
            )
        }
    }
    private fun serversToTableUnits(servers: List<Server>): List<TableUnitItem> {
        return servers.map { server ->
            ServerUnitItem(
                theme = theme,
                itemId = server.id.toLong(),
                server = server
            )
        }
    }

    object Ids {
        object List : ListWidget.Id
    }
}