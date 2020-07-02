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

    val client = CompozaApi()

    // Host Mutable Live Data
    private val _hostname = MutableLiveData("initString")
    private val _os = MutableLiveData("initString")
    private val _ip = MutableLiveData("initString")
    private val _kernel = MutableLiveData("initString")
    private val _uptime = MutableLiveData("initString")
    private val _date = MutableLiveData("initString")
    private val _updates = MutableLiveData("initString")

    // Host Live Data
    val hostname: LiveData<StringDesc> = _hostname.map { it.desc() }
    val os: LiveData<StringDesc> = _os.map { it.desc() }
    val ip: LiveData<StringDesc> = _ip.map { it.desc() }
    val kernel: LiveData<StringDesc> = _kernel.map { it.desc() }
    val uptime: LiveData<StringDesc> = _uptime.map { it.desc() }
    val date: LiveData<StringDesc> = _date.map { it.desc() }
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
                        _hostname.value = resObject.data.host.hostname
                        _os.value = resObject.data.host.os
                        _ip.value = resObject.data.host.ip
                        _kernel.value = resObject.data.host.kernel
                        _uptime.value = resObject.data.host.uptime
                        _date.value = resObject.data.host.date
                        _updates.value = resObject.data.host.updates.toString()
                        println(_updates.value)

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