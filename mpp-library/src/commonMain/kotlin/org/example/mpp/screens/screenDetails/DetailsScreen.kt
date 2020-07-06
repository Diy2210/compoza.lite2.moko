package org.example.mpp.screens.screenDetails

import com.russhwolf.settings.Settings
import com.russhwolf.settings.invoke
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.units.TableUnitItem
import dev.icerock.moko.widgets.*
import dev.icerock.moko.widgets.core.Image
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.core.Widget
import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.WidgetScreen
import dev.icerock.moko.widgets.screen.getViewModel
import dev.icerock.moko.widgets.screen.navigation.NavigationBar
import dev.icerock.moko.widgets.screen.navigation.NavigationItem
import dev.icerock.moko.widgets.style.view.FontStyle
import dev.icerock.moko.widgets.style.view.SizeSpec
import dev.icerock.moko.widgets.style.view.TextStyle
import dev.icerock.moko.widgets.style.view.WidgetSize
import dev.icerock.moko.widgets.style.view.WidgetSize.Companion.AsParent
import dev.icerock.moko.widgets.style.view.WidgetSize.Companion.WidthAsParentHeightWrapContent
import kotlinx.serialization.*
import org.example.library.MR
import org.example.mpp.models.DiskModel
import org.example.mpp.screens.screenServerList.ServerUnitItem
import org.example.mpp.screens.screenServerList.ServerViewModel
import org.example.mpp.theme.AppTheme

class DetailsScreen @ImplicitReflectionSerializer constructor(
    private val theme: Theme,
    private val viewModelFactory: () -> DetailsModel
) : WidgetScreen<Args.Empty>(), NavigationItem {

    override val navigationBar
        get() = NavigationBar.Normal(
            title = MR.strings.compoza_lite.desc(),
            styles = NavigationBar.Styles(
                backgroundColor = Color(0x3155ABFF),
                tintColor = Color(0xffffffffFF),
                textStyle = TextStyle(
                    color = Color(0xffffffffFF),
                    size = 18,
                    fontStyle = FontStyle.BOLD
                )
            )
        )

    private val settings: Settings = Settings()
    var title = settings.getString("Server Title")
    var url = settings.getString("Server Url")
    var token = settings.getString("Server Token")

    @UnstableDefault
    @ImplicitReflectionSerializer
    override fun createContentWidget(): Widget<WidgetSize.Const<SizeSpec.AsParent, SizeSpec.AsParent>> {

        val viewModel = getViewModel {
            viewModelFactory()
        }

        return with(theme) {
            scroll(
                id = Ids.RootScroll,
                size = AsParent,
                child = linear(
                    id = Ids.RootLinearId,
                    size = WidthAsParentHeightWrapContent
                ) {
                    +linear(
                        id = Ids.HostLinearId,
                        size = WidthAsParentHeightWrapContent
                    ) {
                        +text(
                            id = Ids.ServerName,
                            category = AppTheme.TextStyleCategory,
                            size = WidthAsParentHeightWrapContent,
                            text = const(title)
                        )
                        +text(
                            category = AppTheme.TextStyleHostTitle,
                            size = WidthAsParentHeightWrapContent,
                            text = const("System Hostmane")
                        )
                        +text(
                            id = Ids.Url,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.hostname
                        )
                        +text(
                            category = AppTheme.TextStyleHostTitle,
                            size = WidthAsParentHeightWrapContent,
                            text = const("System OS")
                        )
                        +text(
                            id = Ids.System,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.os
                        )
                        +text(
                            category = AppTheme.TextStyleHostTitle,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Public IP")
                        )
                        +text(
                            id = Ids.IP,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.ip
                        )
                        +text(
                            category = AppTheme.TextStyleHostTitle,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Kernel and CPU")
                        )
                        +text(
                            id = Ids.CPU,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.kernel
                        )
                        +text(
                            category = AppTheme.TextStyleHostTitle,
                            size = WidthAsParentHeightWrapContent,
                            text = const("System Uptime")
                        )
                        +text(
                            id = Ids.SystemUptime,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.uptime
                        )
                        +text(
                            category = AppTheme.TextStyleHostTitle,
                            size = WidthAsParentHeightWrapContent,
                            text = const("System Date")
                        )
                        +text(
                            id = Ids.SystemDate,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.date
                        )
                    }

                    +constraint(size = AsParent) {
                        val updates = +text(
                            category = AppTheme.TextStyleHostTitle,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Updates")
                        )
                        val updatesAvalaible = +text(
                            id = Ids.Updates,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Avalaible: ")
                        )
                        val updatesValue = +text(
                            id = Ids.Updates,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.updates.map { it }
                        )

                        constraints {
                            updates topToTop root offset 0
                            updates leftRightToLeftRight root offset 8

                            updatesAvalaible topToBottom updates offset 0
                            updatesAvalaible leftToLeft root offset 8

                            updatesValue topToBottom updates offset 0
                            updatesValue leftToLeft updatesAvalaible offset 60
                        }
                    }

                    // Disk Constraint
                    +constraint(size = AsParent) {
                        val diskUsage = +text(
                            id = Ids.DiskUsageId,
                            category = AppTheme.TextStyleCategory,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Disk Usage")
                        )

                        val list = +list(
                            size = AsParent,
                            id = Ids.List,
                            items = viewModel.diskInfoList.map {
                                diskToTableUnits(it, viewModel)
                            }
                        )

                        // Disk Info Content
//                        val mountPoint = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidthAsParentHeightWrapContent,
//                            text = const("Mount Point")
//                        )
//                        val mountPointValue = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidthAsParentHeightWrapContent,
//                            text = const("-")
//                        )
//                        val free = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidthAsParentHeightWrapContent,
//                            text = const("Free")
//                        )
//                        val freeValue = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidthAsParentHeightWrapContent,
//                            text = const("-")
//                        )
//                        val total = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidthAsParentHeightWrapContent,
//                            text = const("Total")
//                        )
//                        val totalValue = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidthAsParentHeightWrapContent,
//                            text = const("-")
//                        )

                        constraints {
                            diskUsage topToTop root offset 8
                            diskUsage leftRightToLeftRight root offset 8

                            list topToBottom diskUsage offset 0
                            list leftRightToLeftRight root offset 8

//                            mountPoint topToBottom diskUsage offset 8
//                            mountPoint leftToLeft root offset 8
//
//                            mountPointValue topToBottom mountPoint offset 8
//                            mountPointValue leftToLeft root offset 8
//
//                            free topToBottom diskUsage offset 8
//                            free leftToLeft mountPoint offset 250
//
//                            freeValue topToBottom free offset 8
//                            freeValue leftToLeft mountPoint offset 250
//
//                            total topToBottom diskUsage offset 8
//                            total leftToLeft mountPoint offset 300
//
//                            totalValue topToBottom total offset 8
//                            totalValue leftToLeft mountPoint offset 300

                        }
                    }

                    // Server Status Constraint
                    +constraint(size = AsParent) {

                        // Server Status Content
                        val serverStatusTitle = +text(
                            category = AppTheme.TextStyleCategory,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Server Status")
                        )
                        val serverNameValue = +text(
                            category = AppTheme.TextStyleDefaultValue,
                            size = WidthAsParentHeightWrapContent,
                            text = const("-")
                        )
                        val serverStatusValue = +text(
                            category = AppTheme.TextStyleDefaultValue,
                            size = WidthAsParentHeightWrapContent,
                            text = const("-")
                        )
                        constraints {
                            serverStatusTitle topToTop root offset 8
                            serverStatusTitle leftRightToLeftRight root offset 8

                            serverNameValue topToBottom serverStatusTitle offset 8
                            serverNameValue leftToLeft root offset 8

                            serverStatusValue topToBottom serverStatusTitle offset 8
                            serverStatusValue leftToLeft serverNameValue offset 300
                        }
                    }

                    // Software Constraint
                    +constraint(
                        size = AsParent
                    ) {

                        // Software Versions Content
                        val software = +text(
                            category = AppTheme.TextStyleCategory,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Software Versions")
                        )
                        val softwareTitle = +text(
                            category = AppTheme.TextStyleDefaultValue,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Title")
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
                            software topToTop root offset 8
                            software leftRightToLeftRight root offset 8

                            softwareTitle topToBottom software offset 8
                            softwareTitle leftToLeft root offset 8

                            imageValue topToBottom software offset 8
                            imageValue leftToLeft softwareTitle offset 300
                        }
                    }
                }
            )
        }
    }

    object Ids {
        object RootLinearId : LinearWidget.Id
        object HostLinearId : LinearWidget.Id
        object DiskUsageLinearId : LinearWidget.Id
        object ServerStatusLinearId : LinearWidget.Id
        object SoftwareInfoLinearId : LinearWidget.Id
        object RootScroll : ScrollWidget.Id
        object ServerName : TextWidget.Id
        object DiskUsageId : TextWidget.Id
        object List : ListWidget.Id
        object Url : TextWidget.Id
        object System : TextWidget.Id
        object IP : TextWidget.Id
        object CPU : TextWidget.Id
        object SystemUptime : TextWidget.Id
        object SystemDate : TextWidget.Id
        object Updates : TextWidget.Id
    }

    @ImplicitReflectionSerializer
    private fun diskToTableUnits(disks: List<DiskModel>, viewModel: DetailsModel): List<TableUnitItem> {
        var index: Long = 0
        return disks.map { disk ->
            DiskUnitItem(
                theme = theme,
                itemId = index++,
                disk = disk
            )
        }
    }
}