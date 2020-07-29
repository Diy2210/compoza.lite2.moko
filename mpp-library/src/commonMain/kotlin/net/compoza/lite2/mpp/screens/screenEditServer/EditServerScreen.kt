package net.compoza.lite2.mpp.screens.screenEditServer

import dev.icerock.moko.graphics.Color
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
import dev.icerock.moko.widgets.style.view.FontStyle
import dev.icerock.moko.widgets.style.view.TextStyle
import dev.icerock.moko.widgets.style.view.WidgetSize
import net.compoza.lite2.library.MR
import net.compoza.lite2.mpp.theme.AppTheme

class EditServerScreen(
    private val theme: Theme,
    private val routeToMain: Route<Unit>,
    private val viewModelFactory: (EventsDispatcher<EditServerViewModel.EventsListener>)
    -> EditServerViewModel
) : WidgetScreen<Args.Empty>(), EditServerViewModel.EventsListener, NavigationItem {

    override val navigationBar
        get() = NavigationBar.Normal(
            title = MR.strings.edit_server.desc(),
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

    override fun createContentWidget() = with(theme) {
        val viewModel = getViewModel {
            viewModelFactory(createEventsDispatcher())
        }

        viewModel.eventsDispatcher.listen(this@EditServerScreen, this@EditServerScreen)

        constraint(size = WidgetSize.AsParent) {
            val serverTitleInput = +input(
                id = Ids.ServerTitle,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                label = const("Server Title"),
                field = viewModel.serverTitleField
            )

            val serverUrlInput = +input(
                id = Ids.ServerUrl,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                label = const("Server Url"),
                field = viewModel.serverUrlField
            )

            val serverTokenInput = +input(
                id = Ids.ServerToken,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                label = const("Authorization Token"),
                field = viewModel.serverTokenField
            )

            val saveButton = +button(
                id = Ids.SaveBtn,
                category = AppTheme.SaveBtn,
                size = WidgetSize.WrapContent,
                content = ButtonWidget.Content.Text(Value.data("Save".desc())),
                onTap = viewModel::onSavePressed
            )

            val cancelButton = +button(
                id = Ids.CancelBtn,
                category = AppTheme.CancelBtn,
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
                serverTitleInput topToTop root.safeArea offset 8
                serverTitleInput leftRightToLeftRight root offset 16

                serverUrlInput topToBottom serverTitleInput offset 8
                serverUrlInput leftRightToLeftRight root offset 16

                serverTokenInput topToBottom serverUrlInput offset 8
                serverTokenInput leftRightToLeftRight root offset 16

                cancelButton bottomToBottom root.safeArea offset 16
                cancelButton leftToLeft root.safeArea offset 100

                saveButton bottomToBottom root.safeArea offset 16
                saveButton rightToRight root.safeArea offset 100
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
}