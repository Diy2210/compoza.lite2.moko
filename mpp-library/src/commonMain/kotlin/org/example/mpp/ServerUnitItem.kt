package org.example.mpp

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.constraint
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.style.view.SizeSpec
import dev.icerock.moko.widgets.style.view.WidgetSize
import dev.icerock.moko.widgets.text
import dev.icerock.moko.widgets.units.UnitItemRoot
import dev.icerock.moko.widgets.units.WidgetsTableUnitItem
import org.example.mpp.models.ServerModel

class ServerUnitItem(
    private val theme: Theme, itemId: Long, server: ServerModel
) : WidgetsTableUnitItem<ServerModel>(
    itemId = itemId,
    data = server
) {

    override val reuseId: String = "serverCell"

    override fun createWidget(data: LiveData<ServerModel>): UnitItemRoot {
        return with(theme) {

            constraint(size = WidgetSize.WidthAsParentHeightWrapContent) {

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
        }.let {
            UnitItemRoot.from(it)
        }
    }
}