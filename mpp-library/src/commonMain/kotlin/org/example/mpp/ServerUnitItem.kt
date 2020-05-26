package org.example.mpp

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.constraint
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.style.view.SizeSpec
import dev.icerock.moko.widgets.style.view.WidgetSize
import dev.icerock.moko.widgets.text
import dev.icerock.moko.widgets.units.UnitItemRoot
import dev.icerock.moko.widgets.units.WidgetsTableUnitItem

class ServerUnitItem(
    private val theme: Theme, itemId: Long, server: Server
) : WidgetsTableUnitItem<Server>(
    itemId = itemId,
    data = server
) {
    override val reuseId: String = "serverCell"

    override fun createWidget(data: LiveData<Server>): UnitItemRoot {
        return with(theme) {
            constraint(size = WidgetSize.WidthAsParentHeightWrapContent) {
                val title = +text(
                    size = WidgetSize.Const(
                        width = SizeSpec.MatchConstraint,
                        height = SizeSpec.WrapContent
                    ),
                    text = data.map {
                        it.title.desc()
                    }
                )

                val url = +text(
                    size = WidgetSize.Const(
                        width = SizeSpec.MatchConstraint,
                        height = SizeSpec.WrapContent
                    ),
                    text = data.map {
                        it.url.desc()
                    }
                )

                constraints {
                    url.top pin root.top offset 16
                    url.left pin root.left offset 16
                    url.bottom pin root.bottom offset 16

                    title.left pin url.right offset 8
                    title.right pin root.right offset 16
                    title centerYToCenterY root
                }
            }
        }.let { UnitItemRoot.from(it) }
    }
}