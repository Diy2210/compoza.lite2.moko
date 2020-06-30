package org.example.mpp.screens.screenDetails

import com.russhwolf.settings.Settings
import com.russhwolf.settings.invoke
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.widgets.*
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
import org.example.mpp.theme.AppTheme

class DetailsScreen @ImplicitReflectionSerializer constructor(
    private val theme: Theme,
    private val viewModelFactory: () -> DetailsModel
//    private val resObject: ResponseModel
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
//    override fun createContentWidget() = with(theme) {

        val viewModel = getViewModel {
            viewModelFactory()
        }

        return with(theme) {
//            container(size = WidgetSize.AsParent) {
                scroll(
                    id = Ids.RootScroll,
                    size = AsParent,
                    child = linear(
                        id = Ids.RootLinearId,
                        size = WidthAsParentHeightWrapContent
                    ) {

                        +linear(size = AsParent) {
                            +text(
                                id = Ids.ServerName,
                                category = AppTheme.TextStyleCategory,
                                size = WidthAsParentHeightWrapContent,
//                                text = viewModel.hostname
//                                text = const(viewModel.hostLiveData.value.hostname)
                                text = const(title)
                            )
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
        object Url : TextWidget.Id
        object System : TextWidget.Id
        object IP : TextWidget.Id
        object CPU : TextWidget.Id
        object SystemUptime : TextWidget.Id
        object SystemDate : TextWidget.Id
        object Updates : TextWidget.Id
    }
}

//        container(size = WidgetSize.AsParent) {
//            scroll(
//                id = Ids.RootScroll,
//                size = WidgetSize.AsParent,
//                child = linear(
//                    id = Ids.RootLinearId,
//                    size = WidgetSize.WidthAsParentHeightWrapContent
//                ) {
//                    +linear(
//                        id = Ids.HostLinearId,
//                        size = WidgetSize.WrapContent
//                    ) {
//                        +text(
//                            id = Ids.ServerName,
//                            category = AppTheme.TextStyleCategory,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const(title)
//                        )
//                        +text(
//                            category = AppTheme.TextStyleHostTitle,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("System Hostmane")
//                        )
//                        +text(
//                            id = Ids.Url,
//                            category = AppTheme.TextStyleHostValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
////                        text = const(settings.getString("hostname"))
//                            text = const(widgetData.value.data.host.hostname)
//                        )
//                        +text(
//                            category = AppTheme.TextStyleHostTitle,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("System OS")
//                        )
//                        +text(
//                            id = Ids.System,
//                            category = AppTheme.TextStyleHostValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const(settings.getString("os"))
//                        )
//                        +text(
//                            category = AppTheme.TextStyleHostTitle,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("Public IP")
//                        )
//                        +text(
//                            id = Ids.IP,
//                            category = AppTheme.TextStyleHostValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const(settings.getString("ip"))
//                        )
//                        +text(
//                            category = AppTheme.TextStyleHostTitle,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("Kernel and CPU")
//                        )
//                        +text(
//                            id = Ids.CPU,
//                            category = AppTheme.TextStyleHostValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const(settings.getString("kernel"))
//                        )
//                        +text(
//                            category = AppTheme.TextStyleHostTitle,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("System Uptime")
//                        )
//                        +text(
//                            id = Ids.SystemUptime,
//                            category = AppTheme.TextStyleHostValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const(settings.getString("uptime"))
//                        )
//                        +text(
//                            category = AppTheme.TextStyleHostTitle,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("System Date")
//                        )
//                        +text(
//                            id = Ids.SystemDate,
//                            category = AppTheme.TextStyleHostValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const(settings.getString("date"))
//                        )
//                        +text(
//                            category = AppTheme.TextStyleHostTitle,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("Updates")
//                        )
//                        +text(
//                            id = Ids.Updates,
//                            category = AppTheme.TextStyleHostValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const(
//                                "Available: " + (settings.getInt("updates"))
//                            )
//                        )
//                    }
//
//                    // Disk Constraint
//                    +constraint(size = WidgetSize.AsParent) {
//                        val diskUsage = +text(
//                            id = Ids.DiskUsageId,
//                            category = AppTheme.TextStyleCategory,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("Disk Usage")
//                        )
//
//                        // Disk Info Content
//                        val mountPoint = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("Mount Point")
//                        )
//                        val mountPointValue = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("-")
//                        )
//                        val free = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("Free")
//                        )
//                        val freeValue = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("-")
//                        )
//                        val total = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("Total")
//                        )
//                        val totalValue = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("-")
//                        )
//
//                        constraints {
//                            diskUsage topToTop root offset 8
//                            diskUsage leftRightToLeftRight root offset 8
//
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
//                        }
//                    }
//
//                    // Server Status Constraint
//                    +constraint(size = WidgetSize.AsParent) {
//
//                        // Server Status Content
//                        val serverStatusTitle = +text(
//                            category = AppTheme.TextStyleCategory,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("Server Status")
//                        )
//                        val serverNameValue = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("-")
//                        )
//                        val serverStatusValue = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("-")
//                        )
//                        constraints {
//                            serverStatusTitle topToTop root offset 8
//                            serverStatusTitle leftRightToLeftRight root offset 8
//
//                            serverNameValue topToBottom serverStatusTitle offset 8
//                            serverNameValue leftToLeft root offset 8
//
//                            serverStatusValue topToBottom serverStatusTitle offset 8
//                            serverStatusValue leftToLeft serverNameValue offset 300
//                        }
//                    }
//
//                    // Software Constraint
//                    +constraint(
//                        size = WidgetSize.AsParent
//                    ) {
//
//                        // Software Versions Content
//                        val software = +text(
//                            category = AppTheme.TextStyleCategory,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("Software Versions")
//                        )
//                        val softwareTitle = +text(
//                            category = AppTheme.TextStyleDefaultValue,
//                            size = WidgetSize.WidthAsParentHeightWrapContent,
//                            text = const("Title")
//                        )
//                        val imageValue = +image(
//                            image = const(Image.resource(MR.images.tick_png)),
//                            size = WidgetSize.AspectByWidth(
//                                width = SizeSpec.Exact(30f),
//                                aspectRatio = 1.49f
//                            ),
//                            scaleType = ImageWidget.ScaleType.FIT
//                        )
//
//                        constraints {
//                            software topToTop root offset 8
//                            software leftRightToLeftRight root offset 8
//
//                            softwareTitle topToBottom software offset 8
//                            softwareTitle leftToLeft root offset 8
//
//                            imageValue topToBottom software offset 8
//                            imageValue leftToLeft softwareTitle offset 300
//                        }
//                    }
//                }
//            )
//        }
//    }
//}

//    object Ids {
//        object RootLinearId : LinearWidget.Id
//        object HostLinearId : LinearWidget.Id
//        object DiskUsageLinearId : LinearWidget.Id
//        object ServerStatusLinearId : LinearWidget.Id
//        object SoftwareInfoLinearId : LinearWidget.Id
//        object RootScroll : ScrollWidget.Id
//        object ServerName : TextWidget.Id
//        object DiskUsageId : TextWidget.Id
//        object Url : TextWidget.Id
//        object System : TextWidget.Id
//        object IP : TextWidget.Id
//        object CPU : TextWidget.Id
//        object SystemUptime : TextWidget.Id
//        object SystemDate : TextWidget.Id
//        object Updates : TextWidget.Id
//    }
//}

//@Parcelize
//data class Arg(val resObject: ResponseModel) : Parcelable

