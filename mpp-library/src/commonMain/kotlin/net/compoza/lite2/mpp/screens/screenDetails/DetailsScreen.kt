package net.compoza.lite2.mpp.screens.screenDetails

import dev.icerock.moko.graphics.Color
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.units.TableUnitItem
import dev.icerock.moko.widgets.*
import dev.icerock.moko.widgets.core.Image
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.core.Widget
import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.WidgetScreen
import dev.icerock.moko.widgets.screen.getArgument
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
import net.compoza.lite2.library.MR
import net.compoza.lite2.mpp.models.DiskModel
import net.compoza.lite2.mpp.models.ProgsModel
import net.compoza.lite2.mpp.models.StatusModel
import net.compoza.lite2.mpp.theme.AppTheme

class DetailsScreen @ImplicitReflectionSerializer constructor(
    private val theme: Theme,
    private val viewModelFactory: (itemId: Long, title: String, url: String, token: String) -> DetailsViewModel
) : WidgetScreen<Args.Parcel<DetailsScreen.Arg>>(), NavigationItem {

    override val navigationBar
        get() = NavigationBar.Normal(
            title = getArgument().title.desc(),
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

    @UnstableDefault
    @ImplicitReflectionSerializer
    override fun createContentWidget(): Widget<WidgetSize.Const<SizeSpec.AsParent, SizeSpec.AsParent>> {

        val viewModel = getViewModel {
            viewModelFactory(getArgument().itemId, getArgument().title, getArgument().url, getArgument().token)
        }

        return with(theme) {
            scroll(
                id = Ids.RootScroll,
                size = AsParent,
                child = linear(
                    id = Ids.RootLinearId,
                    size = WidthAsParentHeightWrapContent
                ) {

                    // Host Constraint
                    +constraint(
                        id = Ids.HostConstraintId,
                        size = WidthAsParentHeightWrapContent
                    ) {

                        // Hist Info Content
                        val serverTitleName = +text(
                            id = Ids.ServerName,
                            category = AppTheme.TextStyleCategory,
                            size = WidthAsParentHeightWrapContent,
                            text = const(viewModel.title)
                        )
                        val hostname = +text(
                            category = AppTheme.TextStyleHostTitle,
                            size = WidthAsParentHeightWrapContent,
                            text = const("System Hostmane")
                        )
                        val hostnameValue = +text(
                            id = Ids.Url,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.hostname
                        )
                        val os = +text(
                            category = AppTheme.TextStyleHostTitle,
                            size = WidthAsParentHeightWrapContent,
                            text = const("System OS")
                        )
                        val osValue = +text(
                            id = Ids.System,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.os
                        )
                        val ip = +text(
                            category = AppTheme.TextStyleHostTitle,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Public IP")
                        )
                        val ipValue = +text(
                            id = Ids.IP,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.ip
                        )
                        val kernel = +text(
                            category = AppTheme.TextStyleHostTitle,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Kernel and CPU")
                        )
                        val kernelValue = +text(
                            id = Ids.Kernel,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.kernel
                        )
                        val uptime = +text(
                            category = AppTheme.TextStyleHostTitle,
                            size = WidthAsParentHeightWrapContent,
                            text = const("System Uptime")
                        )
                        val uptimeValue = +text(
                            id = Ids.SystemUptime,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.uptime
                        )
                        val date = +text(
                            category = AppTheme.TextStyleHostTitle,
                            size = WidthAsParentHeightWrapContent,
                            text = const("System Date")
                        )
                        val dateValue = +text(
                            id = Ids.SystemDate,
                            category = AppTheme.TextStyleHostValue,
                            size = WidthAsParentHeightWrapContent,
                            text = viewModel.date
                        )

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
                            serverTitleName topToTop root offset 8
                            serverTitleName leftRightToLeftRight root offset 8

                            hostname topToBottom  serverTitleName offset 8
                            hostname leftRightToLeftRight root offset 8

                            hostnameValue topToBottom hostname offset 0
                            hostnameValue leftRightToLeftRight root offset 8

                            os topToBottom hostnameValue offset 8
                            os leftRightToLeftRight root offset 8

                            osValue topToBottom os offset 0
                            osValue leftRightToLeftRight root offset 8

                            ip topToBottom osValue offset 8
                            ip leftRightToLeftRight root offset 8

                            ipValue topToBottom ip offset 0
                            ipValue leftRightToLeftRight root offset 8

                            kernel topToBottom ipValue offset 8
                            kernel leftRightToLeftRight root offset 8

                            kernelValue topToBottom kernel offset 0
                            kernelValue leftRightToLeftRight root offset 8

                            uptime topToBottom kernelValue offset 8
                            uptime leftRightToLeftRight root offset 8

                            uptimeValue topToBottom uptime offset 0
                            uptimeValue leftRightToLeftRight root offset 8

                            date topToBottom uptimeValue offset 8
                            date leftRightToLeftRight root offset 8

                            dateValue topToBottom date offset 0
                            dateValue leftRightToLeftRight root offset 8

                            updates topToBottom dateValue offset 8
                            updates leftRightToLeftRight root offset 8

                            updatesAvalaible topToBottom updates offset 0
                            updatesAvalaible leftToLeft root offset 8

                            updatesValue topToBottom updates offset 0
                            updatesValue leftToLeft updatesAvalaible offset 60

                            warningImage topToBottom updates offset 0
                            warningImage rightToRight root offset 8
                        }
                    }


                    // Server Status Constraint
                    +constraint(size = WidthAsParentHeightWrapContent) {

                        // Server Status Content
                        val serverStatusTitle = +text(
                            category = AppTheme.TextStyleCategory,
                            size = WidthAsParentHeightWrapContent,
                            text = const("Server Status")
                        )

                        val listStatus = +list(
                            size = WidthAsParentHeightWrapContent,
                            id = Ids.ListStatus,
                            items = viewModel.statusInfoArray.map {
                                statusToTableUnits(it, viewModel)
                            }
                        )

                        constraints {
                            serverStatusTitle topToTop root offset 8
                            serverStatusTitle leftRightToLeftRight root offset 8

                            listStatus topToBottom serverStatusTitle offset 0
                            listStatus leftRightToLeftRight root offset 0
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
        object HostConstraintId : ConstraintWidget.Id
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
        object Kernel : TextWidget.Id
        object SystemUptime : TextWidget.Id
        object SystemDate : TextWidget.Id
        object Updates : TextWidget.Id
    }

    @Parcelize
    data class Arg(val itemId: Long, val title: String, val url: String, val token: String): Parcelable
}