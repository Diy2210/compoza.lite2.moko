// MOVE:APP_ID

package net.compoza.lite2.app

import android.app.Application
import net.compoza.lite2.appContext
import net.compoza.lite2.mpp.App

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        mppApplication = App().apply { initialize() }

        appContext = this
    }

    companion object {
        lateinit var mppApplication: App
    }
}
