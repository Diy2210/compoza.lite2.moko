package org.example.mpp

import dev.icerock.moko.widgets.core.Theme
import dev.icerock.moko.widgets.screen.Args
import dev.icerock.moko.widgets.screen.BaseApplication
import dev.icerock.moko.widgets.screen.ScreenDesc

class App : BaseApplication() {
    override fun setup(): ScreenDesc<Args.Empty> {
        val theme = Theme()
        val viewModelFactory = createEventsDispatcher()

        return registerScreen(MainScreen::class) {
            MainScreen(theme, viewModelFactory)
        }
    }
}
