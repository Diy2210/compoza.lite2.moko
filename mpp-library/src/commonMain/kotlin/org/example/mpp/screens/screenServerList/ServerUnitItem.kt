package org.example.mpp.screens.screenServerList

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.*
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.style.view.SizeSpec
import dev.icerock.moko.widgets.style.view.WidgetSize
import dev.icerock.moko.widgets.units.UnitItemRoot
import dev.icerock.moko.widgets.units.WidgetsTableUnitItem

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

                    constraints {
                        title topToTop root offset 16
                        title leftRightToLeftRight root offset 16

                        url topToBottom title offset 8
                        url leftRightToLeftRight root offset 16
                    }
                }
            )
        }.let { UnitItemRoot.from(it) }
    }
}