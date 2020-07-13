package net.compoza.lite2.mpp.screens.screenDetails

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
import net.compoza.lite2.mpp.models.DiskModel
import net.compoza.lite2.mpp.theme.AppTheme

class DiskInfoUnitItem(
    private val theme: Theme,
    itemId: Long,
    disk: DiskModel
) : WidgetsTableUnitItem<DiskModel>(itemId, disk) {

    override val reuseId: String = "diskCell"

    override fun createWidget(data: LiveData<DiskModel>): UnitItemRoot {
        return with(theme) {
            constraint(size = WidgetSize.WidthAsParentHeightWrapContent) {
                val dir = +text(
                    category = AppTheme.TextStyleDefaultValue,
                    size = WidgetSize.Const(
                        width = SizeSpec.WrapContent,
                        height = SizeSpec.WrapContent
                    ),
                    text = data.map {
                        it.dir.desc() as StringDesc
                    }
                )

                // Free Percents Value
                val percent = +text(
                    category = AppTheme.TextStyleBoldValue,
                    size = WidgetSize.Const(
                        width = SizeSpec.WrapContent,
                        height = SizeSpec.WrapContent
                    ),
                    text = data.map {
                        val longFreePercents = it.free.toLongOrNull()
                        val longTotalPercents = it.total.toLongOrNull()
                        if (longFreePercents != null && longTotalPercents != null) {
                            val freePercents = (longFreePercents * 100) / longTotalPercents
                            "$freePercents %".desc()
                        } else {
                            it.total.desc()
                        } as StringDesc
                    }
                )

                // Free Value
                val free = +text(
                    category = AppTheme.TextStyleBoldValue,
                    size = WidgetSize.Const(
                        width = SizeSpec.WrapContent,
                        height = SizeSpec.WrapContent
                    ),
                    text = data.map {
                        val longFree = it.free.toLongOrNull()
                        if (longFree != null) {
                            "(${bytesToGB(longFree)} GB)".desc()
                        } else {
                            it.total.desc()
                        } as StringDesc
                    }
                )

                // Total Value
                val total = +text(
                    category = AppTheme.TextStyleBoldValue,
                    size = WidgetSize.Const(
                        width = SizeSpec.WrapContent,
                        height = SizeSpec.WrapContent
                    ),
                    text = data.map {
                        val longTotal = it.total.toLongOrNull()
                        if (longTotal != null) {
                            "${bytesToGB(longTotal)} GB".desc()
                        } else {
                            it.total.desc()
                        } as StringDesc
                    }
                )

                constraints {
                    dir topToTop root offset 8
                    dir leftToLeft root offset 8

                    total topToTop root offset 8
                    total rightToRight root offset 8

                    free topToTop root offset 8
                    free rightToRight total offset 80

                    percent topToTop root offset 8
                    percent rightToRight free offset 40
                }
            }
        }.let { UnitItemRoot.from(it) }
    }

    // Convert Bytes to KB
    private fun bytesToKB(number: Long): Long = number / 1000

    // Convert Bytes to MB
    private fun bytesToMB(number: Long): Long = bytesToKB(number) / 1000

    // Convert Bytes to GB
    private fun bytesToGB(number: Long): Long = bytesToMB(number) / 1000
}
