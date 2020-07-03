package org.example.mpp.screens.screenDetails

import com.russhwolf.settings.Settings
import com.russhwolf.settings.invoke
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import kotlinx.coroutines.launch
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.example.mpp.api.CompozaApi
import org.example.mpp.models.*

@OptIn(UnstableDefault::class)
@ImplicitReflectionSerializer
class DetailsModel : ViewModel() {
    val settings: Settings = Settings()
    var id = settings.getInt("Server ID")
    var title = settings.getString("Server Title")
    var url = settings.getString("Server Url")
    var token = settings.getString("Server Token")

    private val client = CompozaApi()

    // Host Mutable Live Data
    private val _hostMutableLiveData = MutableLiveData(HostModel(
        "Loading",
        "Loading",
        "Loading",
        "Loading",
        "Loading",
        0,
        "Loading"))
    private val _updates = MutableLiveData("Loading")

    // Host Live Data
    val hostname: LiveData<StringDesc> = _hostMutableLiveData.map { it.hostname.desc() }
    val os: LiveData<StringDesc> = _hostMutableLiveData.map { it.os.desc() }
    val ip: LiveData<StringDesc> = _hostMutableLiveData.map { it.ip.desc() }
    val kernel: LiveData<StringDesc> = _hostMutableLiveData.map { it.kernel.desc() }
    val uptime: LiveData<StringDesc> = _hostMutableLiveData.map { it.uptime.desc() }
    val date: LiveData<StringDesc> = _hostMutableLiveData.map { it.date.desc() }
    val updates: LiveData<StringDesc> = _updates.map { it.desc() }

    // Status Live Data
    // Progs Live Data
    // Disk Live Data

    init {
        launchAsyncRequest()
    }

    @UnstableDefault
    @ImplicitReflectionSerializer
    fun launchAsyncRequest() {
        viewModelScope.launch {
            try {
                client.getStatusServer(url, "/api/v1.1/info", token).also { response ->
                    if (response.contains("success")) {
                        val json = Json(JsonConfiguration.Default)
                        val resObject = json.parse(ResponseModel.serializer(), response)

                        // Host Value
                        _hostMutableLiveData.value = HostModel(
                            hostname = resObject.data.host.hostname,
                            os = resObject.data.host.os,
                            ip = resObject.data.host.ip,
                            kernel = resObject.data.host.kernel,
                            uptime = resObject.data.host.uptime,
                            date = resObject.data.host.date,
                            updates = resObject.data.host.updates
                        )
                        _updates.value = resObject.data.host.updates.toString()
                    } else {
                        println("Error")
                    }
                }
            } catch (exception: Exception) {
                println("Server Error")
            }
        }
    }
}