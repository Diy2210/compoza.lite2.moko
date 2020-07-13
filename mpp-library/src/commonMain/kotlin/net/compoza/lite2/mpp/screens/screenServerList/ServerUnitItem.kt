package net.compoza.lite2.mpp.screens.screenServerList

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.*
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.core.Value
import dev.icerock.moko.widgets.style.view.SizeSpec
import dev.icerock.moko.widgets.style.view.WidgetSize
import dev.icerock.moko.widgets.units.UnitItemRoot
import dev.icerock.moko.widgets.units.WidgetsTableUnitItem
import net.compoza.lite2.mpp.theme.AppTheme

class ServerUnitItem(
    private val theme: Theme, itemId: Long, server: ServerItem,
    private val clickListener: (ServerItem) -> Unit
) : WidgetsTableUnitItem<ServerItem>(itemId, server) {

    override val reuseId: String = "serverCell"

    override fun createWidget(data: LiveData<ServerItem>): UnitItemRoot {
        return with(theme) {
            clickable(
                onClick = {
                    clickListener(data.value)
                },
                child = constraint(size = WidgetSize.WidthAsParentHeightWrapContent) {
                    val title = +text(
                        size = WidgetSize.Const(
                            width = SizeSpec.MatchConstraint,
                            height = SizeSpec.WrapContent
                        ),
                        text = data.map {
                            it.title.desc() as StringDesc
                        }
                    )

                    val url = +text(
                        size = WidgetSize.Const(
                            width = SizeSpec.MatchConstraint,
                            height = SizeSpec.WrapContent
                        ),
                        text = data.map {
                            it.url.desc() as StringDesc
                        }
                    )

                    val menuBtn = +button(
                        category = AppTheme.MenuBtn,
                        size = WidgetSize.Const(SizeSpec.Exact(50f), SizeSpec.Exact(50f)),
                        content = ButtonWidget.Content.Text(Value.data("⋮".desc())),
                        onTap = { println("Click") }
                    )

                    constraints {
                        title topToTop root offset 16
                        title leftRightToLeftRight root offset 16

                        url topToBottom title offset 8
                        url leftRightToLeftRight root offset 16

                        menuBtn topToTop root offset 8
                        menuBtn bottomToBottom root offset 0
                        menuBtn rightToRight root offset 8
                    }
                }
            )
        }.let { UnitItemRoot.from(it) }
    }
}