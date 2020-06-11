package org.example.mpp.units

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
import org.example.mpp.models.ServerModel

class ServerUnitItem(
    private val theme: Theme, itemId: Long, server: ServerModel,
    private val clickListener: (ServerModel) -> Unit
) : WidgetsTableUnitItem<ServerModel>(itemId, server) {

    override val reuseId: String = "serverCell"

    override fun createWidget(data: LiveData<ServerModel>): UnitItemRoot {
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