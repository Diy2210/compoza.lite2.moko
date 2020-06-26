package org.example.mpp.screens.screenDetails

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.invoke
import com.russhwolf.settings.set
import dev.icerock.moko.fields.FormField
import dev.icerock.moko.fields.liveBlock
import dev.icerock.moko.graphics.Color
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import dev.icerock.moko.resources.desc.desc
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
import dev.icerock.moko.widgets.utils.asLiveData
import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.set
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.example.library.MR
import org.example.mpp.api.CompozaApi
import org.example.mpp.models.DataModel
import org.example.mpp.models.DiskModel
import org.example.mpp.models.ResponseModel
import org.example.mpp.screens.screenEditServer.EditServerScreen
import org.example.mpp.theme.AppTheme

class DetailsScreen(
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

//    val client = CompozaApi()

//    private var widgetData = MutableLiveData<ResponseModel>("")
//    var resObject = ResponseModel.serializer()

//    @UnstableDefault
//    @ImplicitReflectionSerializer
//    private fun launchAsyncRequest() {
//        MainScope().launch {
//            try {
//                client.getStatusServer(url, "/api/v1.1/info", token).also { response ->
//                    if (response.contains("success")) {
////                        widgetData.value = response
//
//                        val json = Json(JsonConfiguration.Default)
////                        val resObject = json.parse(ResponseModel.serializer(), response)
//                        resObject = json.parse(response)
////                        widgetData = json.parse(response)
//                        println("////////${resObject}")
//                    } else {
////                        widgetData.value = "Error"
//                        println("SERVER ERROR")
//                    }
//                }
//            } catch (e: Exception) {
//                println(e)
//            }
//        }
//    }

    //    override fun createContentWidget() = with(theme) {

    @UnstableDefault
    @ImplicitReflectionSerializer
//    override fun createContentWidget(): Widget<WidgetSize.Const<SizeSpec.AsParent, SizeSpec.AsParent>> {
    override fun createContentWidget() = with(theme) {
        val viewModel = getViewModel {
            viewModelFactory()
        }

        viewModel.launchAsyncRequest()

//        return with(theme) {
            linear(
                size = WidgetSize.AsParent
            ) {
                +text(
                    size = WidgetSize.WidthAsParentHeightWrapContent,
                    text = const(viewModel.hostname)
                )
//            }
        }
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

