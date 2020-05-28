package org.example.mpp.screens

import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.ButtonWidget
import dev.icerock.moko.widgets.TextWidget
import dev.icerock.moko.widgets.constraint
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.WidgetScreen
import dev.icerock.moko.widgets.screen.navigation.NavigationBar
import dev.icerock.moko.widgets.screen.navigation.NavigationItem
import dev.icerock.moko.widgets.screen.navigation.Route
import dev.icerock.moko.widgets.style.view.WidgetSize
import dev.icerock.moko.widgets.text
import org.example.library.MR

class DetailsScreen(
    private val theme: Theme
) : WidgetScreen<Args.Empty>(), NavigationItem {

    override val navigationBar: NavigationBar = NavigationBar.Normal(MR.strings.compoza_lite.desc())

    override fun createContentWidget() = with(theme) {

        constraint(size = WidgetSize.AsParent) {
            val title = +text(
                id = Ids.ServerName,
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const("Server Name")
            )

            constraints {
                title topToTop root offset 16
                title leftToLeft root offset 16
            }
        }
    }

    object Ids {
        object ServerName : TextWidget.Id
    }
}