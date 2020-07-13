package org.example.mpp.screens.screenServerList

import dev.icerock.moko.graphics.Color
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.units.TableUnitItem
import dev.icerock.moko.widgets.*
import dev.icerock.moko.widgets.core.Image
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.core.Value
import dev.icerock.moko.widgets.screen.*
import dev.icerock.moko.widgets.screen.navigation.NavigationBar
import dev.icerock.moko.widgets.screen.navigation.NavigationItem
import dev.icerock.moko.widgets.screen.navigation.Route
import dev.icerock.moko.widgets.screen.navigation.route
import dev.icerock.moko.widgets.style.view.FontStyle
import dev.icerock.moko.widgets.style.view.SizeSpec
import dev.icerock.moko.widgets.style.view.TextStyle
import dev.icerock.moko.widgets.style.view.WidgetSize
import kotlinx.serialization.ImplicitReflectionSerializer
import org.example.library.MR
import org.example.mpp.theme.AppTheme

class ServerListScreen(
    private val theme: Theme,
    private val routeEditServer: Route<Unit>,
    private val routeDetails: Route<Unit>,
    private val viewModelFactory: (EventsDispatcher<ServerViewModel.EventsListener>) -> ServerViewModel
) : WidgetScreen<Args.Empty>(), ServerViewModel.EventsListener, NavigationItem {

    override val navigationBar
        get() = NavigationBar.Normal(
            title = MR.strings.compoza_lite.desc(),
            styles = NavigationBar.Styles(
                backgroundColor = Color(0x3155ABFF),
                tintColor = Color(0x3155ABFF),
                textStyle = TextStyle(
                    color = Color(0xffffffffFF),
                    size = 18,
                    fontStyle = FontStyle.BOLD
                )
            )
        )

    @ImplicitReflectionSerializer
    override fun createContentWidget() = with(theme) {
        val viewModel = getViewModel {
            viewModelFactory(createEventsDispatcher())
        }

        viewModel.eventsDispatcher.listen(this@ServerListScreen, this@ServerListScreen)

        constraint(size = WidgetSize.AsParent) {
            val list = +list(
                size = WidgetSize.AsParent,
                id = Ids.List,
                items = viewModel.servers.map {
                    serversToTableUnits(it, viewModel)
                }
            )

            val createNewServer = +button(
                id = Ids.CreateNewServer,
                category = AppTheme.PlusBtn,
                size = WidgetSize.Const(SizeSpec.Exact(50f), SizeSpec.Exact(50f)),
                content = ButtonWidget.Content.Text(Value.data("+".desc())),
                onTap = viewModel::onAddPressed
            )

            constraints {
                list topToBottom root offset 8
                list leftToRight root offset 8

                createNewServer bottomToBottom root offset 20
                createNewServer rightToRight root offset 20
            }
        }
    }

    object Ids {
        object List : ListWidget.Id
        object CreateNewServer : ButtonWidget.Id
    }

    override fun routeToEditServer() {
        routeEditServer.route()
    }

    override fun routeToDetails() {
        routeDetails.route()
    }

    @ImplicitReflectionSerializer
    private fun serversToTableUnits(servers: List<ServerItem>, viewModel: ServerViewModel): List<TableUnitItem> {
        return servers.map { server ->
            ServerUnitItem(
                theme = theme,
                itemId = server.ID.toLong(),
                server = server,
                clickListener = {
                    viewModel.onClickToItem(it)
                }
            )
        }
    }
}