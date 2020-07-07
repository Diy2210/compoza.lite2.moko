package org.example.mpp.screens.screenDetails

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.ImageWidget
import dev.icerock.moko.widgets.constraint
import dev.icerock.moko.widgets.core.Image
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.image
import dev.icerock.moko.widgets.style.view.SizeSpec
import dev.icerock.moko.widgets.style.view.WidgetSize
import dev.icerock.moko.widgets.text
import dev.icerock.moko.widgets.units.UnitItemRoot
import dev.icerock.moko.widgets.units.WidgetsTableUnitItem
import org.example.library.MR
import org.example.mpp.models.StatusModel
import org.example.mpp.theme.AppTheme

class StatusInfoUnitItem(
    private val theme: Theme,
    itemId: Long,
    status: StatusModel
) : WidgetsTableUnitItem<StatusModel>(itemId, status) {

    override val reuseId: String = "statusCell"

    override fun createWidget(data: LiveData<StatusModel>): UnitItemRoot {
        return with(theme) {
            constraint(size = WidgetSize.WidthAsParentHeightWrapContent) {
                val dir = +text(
                    category = AppTheme.TextStyleDefaultValue,
                    size = WidgetSize.Const(
                        width = SizeSpec.WrapContent,
                        height = SizeSpec.WrapContent
                    ),
                    text = data.map {
                        it.name.desc() as StringDesc
                    }
                )

                val imageValue = +image(
                    image = const(Image.resource(MR.images.tick_png)),
                    size = WidgetSize.AspectByWidth(
                        width = SizeSpec.Exact(30f),
                        aspectRatio = 1.49f
                    ),
                    scaleType = ImageWidget.ScaleType.FIT
                )

                constraints {
                    dir topToTop root offset 8
                    dir leftToLeft root offset 8

                    imageValue topToTop root offset 8
                    imageValue leftToLeft dir offset 300
                }
            }
        }.let { UnitItemRoot.from(it) }
    }
}