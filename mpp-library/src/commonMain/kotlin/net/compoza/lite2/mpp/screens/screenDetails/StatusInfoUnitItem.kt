package net.compoza.lite2.mpp.screens.screenDetails

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
import net.compoza.lite2.library.MR
import net.compoza.lite2.mpp.models.StatusModel
import net.compoza.lite2.mpp.theme.AppTheme

class StatusInfoUnitItem(
    private val theme: Theme,
    itemId: Long,
    status: StatusModel
) : WidgetsTableUnitItem<StatusModel>(itemId, status) {

    override val reuseId: String = "statusCell"

    override fun createWidget(data: LiveData<StatusModel>): UnitItemRoot {
        return with(theme) {

            constraint(size = WidgetSize.WidthAsParentHeightWrapContent) {
                val name = +text(
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
                    size = WidgetSize.AspectByWidth(
                        width = SizeSpec.Exact(30f),
                        aspectRatio = 1.49f
                    ),
                    scaleType = ImageWidget.ScaleType.FIT,
                    image = data.map {
                        if (it.status == ("0")) {
                            Image.resource(MR.images.empty_png)
                        } else {
                            Image.resource(MR.images.tick_png)
                        }
                    }
                )

                constraints {
                    name topToTop root offset 8
                    name leftToLeft root offset 8

                    imageValue topToTop root offset 8
                    imageValue rightToRight root offset 8
                }
            }
        }.let { UnitItemRoot.from(it) }
    }
}