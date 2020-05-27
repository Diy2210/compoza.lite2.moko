package org.example.mpp.screens

import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.resources.desc.StringDesc
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
import org.example.mpp.models.EditServerViewModel

class EditServerScreen(
    private val theme: Theme,
    private val routeToMain: Route<Unit>,
    private val viewModelFactory: (EventsDispatcher<EditServerViewModel.EventsListener>)
    -> EditServerViewModel
) : WidgetScreen<Args.Empty>(), EditServerViewModel.EventsListener, NavigationItem {

    override val navigationBar: NavigationBar = NavigationBar.Normal(MR.strings.compoza_lite.desc())

    override fun createContentWidget() = with(theme) {
        val viewModel = getViewModel {
            viewModelFactory(createEventsDispatcher())
        }

        viewModel.eventsDispatcher.listen(this@EditServerScreen, this@EditServerScreen)

        constraint(size = WidgetSize.AsParent) {

//            val title = +text(
//                size = WidgetSize.WidthAsParentHeightWrapContent,
//                text = const("Add New Server")
//            )

            val serverTitleInput = +input(
                id = Ids.ServerTitle,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                label = const("Server Title"),
//                field = FormField(initialValue = "", validation = liveBlock { null })
                field = viewModel.serverTitleField
            )

            val serverUrlInput = +input(
                id = Ids.ServerUrl,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                label = const("Server Url"),
//                field = FormField(initialValue = "", validation = liveBlock { null })
                field = viewModel.serverUrlField
            )

            val serverTokenInput = +input(
                id = Ids.ServerToken,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                label = const("Authorization Token"),
//                field = FormField(initialValue = "", validation = liveBlock { null })
                field = viewModel.serverTokenField
            )

            val saveButton = +button(
                id = Ids.SaveBtn,
                size = WidgetSize.WrapContent,
                content = ButtonWidget.Content.Text(Value.data("Save".desc())),
                onTap = viewModel::onSavePressed
            )

            val cancelButton = +button(
                id = Ids.CancelBtn,
                size = WidgetSize.WrapContent,
                content = ButtonWidget.Content.Text(Value.data("Cancel".desc())),
                onTap = viewModel::onCancelPressed
            )
//            {
//                showToast("cancel pressed".desc())
//            screenResult = Result(true)
//            routeBack.route()
//            }

            constraints {
//                title topToTop root offset 16
//                title leftToRight root offset 150

//                serverTitleInput topToBottom title offset 32
                serverTitleInput topToTop root offset 16
                serverTitleInput leftRightToLeftRight root offset 16

                serverUrlInput topToBottom serverTitleInput offset 8
                serverUrlInput leftRightToLeftRight root offset 16

                serverTokenInput topToBottom serverUrlInput offset 8
                serverTokenInput leftRightToLeftRight root offset 16

                cancelButton bottomToBottom root offset 16
                cancelButton leftToLeft root offset 100

                saveButton bottomToBottom root offset 16
                saveButton rightToRight root offset 100
            }
        }
    }

    object Ids {
        object ServerTitle : InputWidget.Id
        object ServerUrl : InputWidget.Id
        object ServerToken : InputWidget.Id
        object SaveBtn : ButtonWidget.Id
        object CancelBtn : ButtonWidget.Id
    }

    override fun showError(error: StringDesc) {
        showToast(error)
    }

    override fun routeToMain() {
        routeToMain.route()
    }

//    override fun routeInputCode(title: String, url: String, token: String) {
//        routeInputCode(title, url, token)
//    }
}