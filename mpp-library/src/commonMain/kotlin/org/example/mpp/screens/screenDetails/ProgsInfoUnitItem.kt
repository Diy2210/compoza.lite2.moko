package org.example.mpp.screens.screenDetails

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.ImageWidget
import dev.icerock.moko.widgets.constraint
import dev.icerock.moko.widgets.core.Image
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.style.view.SizeSpec
import dev.icerock.moko.widgets.style.view.WidgetSize
import dev.icerock.moko.widgets.text
import dev.icerock.moko.widgets.units.UnitItemRoot
import dev.icerock.moko.widgets.units.WidgetsTableUnitItem
import org.example.mpp.models.ProgsModel
import org.example.mpp.theme.AppTheme

class ProgsInfoUnitItem(
    private val theme: Theme,
    itemId: Long,
    prog: ProgsModel
) : WidgetsTableUnitItem<ProgsModel>(itemId, prog) {

    override val reuseId: String = "progsCell"

    override fun createWidget(data: LiveData<ProgsModel>): UnitItemRoot {
        return with(theme) {
            constraint(size = WidgetSize.WidthAsParentHeightWrapContent) {
                val title = +text(
                    category = AppTheme.TextStyleDefaultValue,
                    size = WidgetSize.Const(
                        width = SizeSpec.WrapContent,
                        height = SizeSpec.WrapContent
                    ),
                    text = data.map {
                        it.title.desc() as StringDesc
                    }
                )

                val value = +text(
                    category = AppTheme.TextStyleDefaultValue,
                    size = WidgetSize.Const(
                        width = SizeSpec.WrapContent,
                        height = SizeSpec.WrapContent
                    ),
                    text = data.map {
                        it.value.desc() as StringDesc
                    }
                )

                constraints {
                    title topToTop root offset 8
                    title leftToLeft root offset 8

                    value topToTop root offset 8
                    value leftToLeft title offset 230
                }
            }
        }.let { UnitItemRoot.from(it) }
    }
}