package net.compoza.lite2.mpp.helpers

import dev.icerock.moko.widgets.core.ViewBundle
import dev.icerock.moko.widgets.core.ViewFactory
import dev.icerock.moko.widgets.core.ViewFactoryContext
import dev.icerock.moko.widgets.style.view.WidgetSize

actual class ContextMenuViewFactory actual constructor(
) : ViewFactory<ContextMenuWidget<out WidgetSize>> {

    override fun <WS : WidgetSize> build(
        widget: ContextMenuWidget<out WidgetSize>,
        size: WS,
        viewFactoryContext: ViewFactoryContext
    ): ViewBundle<WS> {
        val childViewBundle =
            widget.child.buildView(viewFactoryContext) as ViewBundle<WS>

        childViewBundle.view.apply {
            // set here context menu showing on click to `this`
            // https://developer.apple.com/design/human-interface-guidelines/ios/controls/context-menus/
            // https://developer.apple.com/design/human-interface-guidelines/ios/views/action-sheets/
        }

        return childViewBundle
    }
}