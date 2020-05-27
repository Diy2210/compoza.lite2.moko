package org.example.mpp.screens

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.*
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.core.Value
import dev.icerock.moko.widgets.screen.*
import dev.icerock.moko.widgets.screen.navigation.NavigationBar
import dev.icerock.moko.widgets.screen.navigation.NavigationItem
import dev.icerock.moko.widgets.screen.navigation.Route
import dev.icerock.moko.widgets.screen.navigation.route
import dev.icerock.moko.widgets.style.view.WidgetSize
import org.example.library.MR
import org.example.mpp.models.NewMainViewModel

class NewMainScreen(
    private val theme: Theme,
    private val routeEditServer: Route<Unit>,
//    private val routeDetails: Route<Unit>,
    private val viewModelFactory: (EventsDispatcher<NewMainViewModel.EventsListener>)
    -> NewMainViewModel
) : WidgetScreen<Args.Empty>(), NewMainViewModel.EventsListener, NavigationItem {

    override val navigationBar: NavigationBar = NavigationBar.Normal(MR.strings.compoza_lite.desc())

    override fun createContentWidget() = with(theme) {

        val viewModel = getViewModel {
            viewModelFactory(createEventsDispatcher())
        }

        viewModel.eventsDispatcher.listen(this@NewMainScreen, this@NewMainScreen)

        constraint(size = WidgetSize.AsParent) {

            val createNewServer = +button(
                id = Ids.createNewServer,
                size = WidgetSize.WrapContent,
                content = ButtonWidget.Content.Text(Value.data("+".desc())),
                onTap = viewModel::onAddPressed
            )

//            val viewDetails = +button(
//                size = WidgetSize.WrapContent,
//                content = ButtonWidget.Content.Text(Value.data("details".desc())),
//                onTap = viewModel::onDetailsPressed
//            )

            constraints {
                createNewServer bottomToBottom root offset 16
                createNewServer rightToRight root offset 16

//                viewDetails bottomToBottom root offset 16
//                viewDetails leftToLeft  root offset 16
            }
        }
    }

    object Ids {
        object createNewServer : ButtonWidget.Id
    }

    override fun routeToEditServer() {
        routeEditServer.route()
    }

//    override fun routeToDetails() {
//        routeDetails.route()
//    }
}
