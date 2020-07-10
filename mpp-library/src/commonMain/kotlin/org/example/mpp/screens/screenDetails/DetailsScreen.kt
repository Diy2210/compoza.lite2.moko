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
import org.example.mpp.models.ProgsModel
import org.example.mpp.models.StatusModel
import org.example.mpp.theme.AppTheme

class DetailsScreen @ImplicitReflectionSerializer constructor(
    private val theme: Theme,
    private val viewModelFactory: () -> DetailsViewModel
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

                    // Host Linear
                    +linear(
                        id = Ids.HostLinearId,
                        size = WidthAsParentHeightWrapContent
                    ) {

                        // Hist Info Content
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
                            text = const("Available: ")
                        )
                        val updatesValue = +text(
                            id = Ids.Updates,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.updates
                        )

                        val warningImage = +image(
                            size = WidgetSize.AspectByWidth(width = SizeSpec.Exact(30f), aspectRatio = 1.49f),
                            image = const(Image.resource(MR.images.warning_png)),
                            scaleType = ImageWidget.ScaleType.FIT
                        )


                        constraints {
                            updates topToTop root offset 0
                            updates leftRightToLeftRight root offset 8

                            updatesAvalaible topToBottom updates offset 0
                            updatesAvalaible leftToLeft root offset 8

                            updatesValue topToBottom updates offset 0
                            updatesValue leftToLeft updatesAvalaible offset 60

                            warningImage topToBottom updates offset 0
                            warningImage rightToRight root offset 8
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

                        // Disk Info Content
                        val mountPoint = +text(
                            category = AppTheme.TextStyleDefaultValue,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Mount Point")
                        )
                        val free = +text(
                            category = AppTheme.TextStyleDefaultValue,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Free")
                        )
                        val total = +text(
                            category = AppTheme.TextStyleDefaultValue,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Total")
                        )

                        val listDisk = +list(
                            size = AsParent,
                            id = Ids.ListDisk,
                            items = viewModel.diskInfoList.map {
                                diskToTableUnits(it, viewModel)
                            }
                        )

                        constraints {
                            diskUsage topToTop root offset 8
                            diskUsage leftRightToLeftRight root offset 8

                            mountPoint topToBottom diskUsage offset 8
                            mountPoint leftToLeft root offset 8

                            free topToBottom diskUsage offset 8
                            free leftToLeft mountPoint offset 275

                            total topToBottom diskUsage offset 8
                            total leftToLeft root offset 375

                            listDisk topToBottom mountPoint offset 0
                            listDisk leftRightToLeftRight root offset 8
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
                        val listStatus = +list(
                            size = AsParent,
                            id = Ids.ListStatus,
                            items = viewModel.statusInfoArray.map {
                                statusToTableUnits(it, viewModel)
                            }
                        )

                        constraints {
                            serverStatusTitle topToTop root offset 8
                            serverStatusTitle leftRightToLeftRight root offset 8

                            listStatus topToBottom serverStatusTitle offset 0
                            listStatus leftRightToLeftRight root offset 8
                        }
                    }

                    // Progs Constraint
                    +constraint(size = AsParent) {

                        // Progs Versions Content
                        val software = +text(
                            category = AppTheme.TextStyleCategory,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Software Versions")
                        )
                        val listProgs = +list(
                            size = AsParent,
                            id = Ids.ListProgs,
                            items = viewModel.progsInfoArray.map {
                                progsToTableUnits(it, viewModel)
                            }
                        )

                        constraints {
                            software topToTop root offset 8
                            software leftRightToLeftRight root offset 8

                            listProgs topToBottom software offset 0
                            listProgs leftRightToLeftRight root offset 8
                            listProgs bottomToBottom root offset 16
                        }
                    }
                }
            )
        }
    }

    // Disk Table Units
    @ImplicitReflectionSerializer
    private fun diskToTableUnits(disks: List<DiskModel>, viewViewModel: DetailsViewModel): List<TableUnitItem> {
        var index: Long = 0
        return disks.map { disk ->
            DiskInfoUnitItem(
                theme = theme,
                itemId = index++,
                disk = disk
            )
        }
    }

    // Status Table Units
    @ImplicitReflectionSerializer
    private fun statusToTableUnits(statuses: List<StatusModel>, viewViewModel: DetailsViewModel): List<TableUnitItem> {
        var index: Long = 0
        return statuses.map { status ->
            StatusInfoUnitItem(
                theme = theme,
                itemId = index++,
                status = status
            )
        }
    }

    // Progs Table Units
    @ImplicitReflectionSerializer
    private fun progsToTableUnits(progs: List<ProgsModel>, viewViewModel: DetailsViewModel): List<TableUnitItem> {
        var index: Long = 0
        return progs.map { prog ->
            ProgsInfoUnitItem(
                theme = theme,
                itemId = index++,
                prog = prog
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
        object ListDisk : ListWidget.Id
        object ListStatus : ListWidget.Id
        object ListProgs : ListWidget.Id
        object Url : TextWidget.Id
        object System : TextWidget.Id
        object IP : TextWidget.Id
        object CPU : TextWidget.Id
        object SystemUptime : TextWidget.Id
        object SystemDate : TextWidget.Id
        object Updates : TextWidget.Id
    }
}