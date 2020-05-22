package org.example.mpp

import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.constraint
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.WidgetScreen
import dev.icerock.moko.widgets.screen.navigation.NavigationBar
import dev.icerock.moko.widgets.screen.navigation.NavigationItem
import dev.icerock.moko.widgets.style.view.WidgetSize
import dev.icerock.moko.widgets.text

class DetailsScreen(
    private val theme: Theme
) : WidgetScreen<Args.Empty>(), NavigationItem {

    override val navigationBar: NavigationBar = NavigationBar.Normal(title = "Compoza Lite".desc())

    override fun createContentWidget() = with(theme) {

        constraint(size = WidgetSize.AsParent) {
            val title = +text(
                size = WidgetSize.WidthAsParentHeightWrapContent,
                text = const("Add New Server")
            )

            constraints {
                title topToTop root offset 16
                title leftToRight root offset 150
            }
        }
    }
}