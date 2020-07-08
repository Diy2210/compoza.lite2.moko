package org.example.mpp.screens.screenDetails

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.resources.desc.plus
import dev.icerock.moko.widgets.constraint
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.style.view.SizeSpec
import dev.icerock.moko.widgets.style.view.WidgetSize
import dev.icerock.moko.widgets.text
import dev.icerock.moko.widgets.units.UnitItemRoot
import dev.icerock.moko.widgets.units.WidgetsTableUnitItem
import org.example.mpp.models.DiskModel
import org.example.mpp.theme.AppTheme

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

                val free = +text(
                    category = AppTheme.TextStyleBoldValue,
                    size = WidgetSize.Const(
                        width = SizeSpec.WrapContent,
                        height = SizeSpec.WrapContent
                    ),
                    text = data.map {
//                        it.free.desc() as StringDesc
                        val longFree = it.free.toLongOrNull()
                        if (longFree != null) {
                            "${bytesToGB(longFree)} Gb".desc()
                        } else {
                            it.total.desc()
                        } as StringDesc
                    }
                )

                val total = +text(
                    category = AppTheme.TextStyleBoldValue,
                    size = WidgetSize.Const(
                        width = SizeSpec.WrapContent,
                        height = SizeSpec.WrapContent
                    ),
                    text = data.map {
                        val longTotal = it.total.toLongOrNull()
                        if (longTotal != null) {
                            "${bytesToGB(longTotal)} Gb".desc()
                        } else {
                            it.total.desc()
                        } as StringDesc
                    }
                )

                constraints {
                    dir topToTop root offset 8
                    dir leftToLeft root offset 8

                    free topToTop root offset 8
                    free leftToLeft dir offset 220

                    total topToTop root offset 8
                    total leftToLeft dir offset 300
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
