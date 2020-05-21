/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package org.example.mpp

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.*
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.core.Value
import dev.icerock.moko.widgets.screen.*
import dev.icerock.moko.widgets.screen.navigation.Route
import dev.icerock.moko.widgets.screen.navigation.route
import dev.icerock.moko.widgets.style.view.WidgetSize

class MainScreen(
    private val theme: Theme,
    private val viewModelFactory: (EventsDispatcher<MainViewModel.EventsListener>)
    -> MainViewModel
) : WidgetScreen<Args.Empty>(), MainViewModel.EventsListener {

    override fun createContentWidget() = with(theme) {

        val viewModel = getViewModel {
            viewModelFactory(createEventsDispatcher())
        }

        viewModel.eventsDispatcher.listen(this@MainScreen, this@MainScreen)

        constraint(size = WidgetSize.AsParent) {

            val createNewServer = +button(
                id = Ids.createNewServer,
                size = WidgetSize.WrapContent,
                content = ButtonWidget.Content.Text(Value.data("+".desc())),
                onTap = viewModel::onAddPressed
            )
//            {
//                showToast("save pressed".desc())
//            }

            constraints {
                createNewServer bottomToBottom root offset 16
                createNewServer rightToRight   root offset 16
            }
        }
    }

    object Ids {
        object createNewServer : ButtonWidget.Id
    }

    override fun routeToEditServer() {
        TODO("Not yet implemented")
        routeToEditServer()
    }
}
