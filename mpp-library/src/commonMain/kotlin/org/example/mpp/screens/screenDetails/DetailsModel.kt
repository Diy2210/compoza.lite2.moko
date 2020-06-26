package org.example.mpp.screens.screenDetails

import com.russhwolf.settings.Settings
import com.russhwolf.settings.invoke
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.StructureKind
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.example.mpp.api.CompozaApi
import org.example.mpp.models.ResponseModel

class DetailsModel : ViewModel() {
    val settings: Settings = Settings()
    var id = settings.getInt("Server ID")
    var title = settings.getString("Server Title")
    var url = settings.getString("Server Url")
    var token = settings.getString("Server Token")

    var hostname: String = ""
    var os: String = ""
    var ip: String = ""
    var kernel: String = ""
    var uptime: String = ""
    var date: String = ""
    var updates: String = ""

    val client = CompozaApi()

    @UnstableDefault
    @ImplicitReflectionSerializer
    fun launchAsyncRequest() {
        viewModelScope.launch {
            try {
                client.getStatusServer(url, "/api/v1.1/info", token).also { response ->
                    if (response.contains("success")) {
                        val json = Json(JsonConfiguration.Default)
                        val resObject = json.parse(ResponseModel.serializer(), response)
                        hostname = resObject.data.host.hostname
                        os = resObject.data.host.os
                        ip = resObject.data.host.ip
                        kernel = resObject.data.host.kernel
                        uptime = resObject.data.host.uptime
                        date = resObject.data.host.date
                        updates = resObject.data.host.updates.toString()
                    } else {
                        println("SERVER ERROR")
                    }
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}