package net.compoza.lite2.mpp.helpers

import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.widgets.core.OptionalId
import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.core.ViewBundle
import dev.icerock.moko.widgets.core.ViewFactory
import dev.icerock.moko.widgets.core.ViewFactoryContext
import dev.icerock.moko.widgets.core.Widget
import dev.icerock.moko.widgets.core.WidgetDef
import dev.icerock.moko.widgets.style.view.WidgetSize

@WidgetDef(ContextMenuViewFactory::class)
class ContextMenuWidget<WS : WidgetSize>(
    private val factory: ViewFactory<ContextMenuWidget<out WidgetSize>>,
    override val id: Id?,
    val child: Widget<WS>,
    val menuItems: List<MenuItem>
) : Widget<WS>(), OptionalId<ContextMenuWidget.Id> {

    override val size: WS = child.size

    override fun buildView(viewFactoryContext: ViewFactoryContext): ViewBundle<WS> {
        return factory.build(this, size, viewFactoryContext)
    }

    interface Id : Theme.Id<ContextMenuWidget<out WidgetSize>>
    interface Category : Theme.Category<ContextMenuWidget<out WidgetSize>>

    object DefaultCategory : Category

    data class MenuItem(
        val title: StringDesc,
        val onClick: () -> Unit
    )
}