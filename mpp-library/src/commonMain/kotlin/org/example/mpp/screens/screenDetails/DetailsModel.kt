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

//    var os: String = ""
//    var ip: String = ""
//    var kernel: String = ""
//    var uptime: String = ""
//    var date: String = ""
//    var updates: Int?

    private val widgetData = MutableLiveData("")

    // Host Live Data
    private val _hostLiveData = MutableLiveData(HostModel(
        hostname = "", os = "", ip = "", kernel = "", uptime = "", date = "", updates = 0))

    var hostname: LiveData<StringDesc> = _hostLiveData.map { it.hostname.desc() }


//    val hostLiveData: LiveData<HostModel> = _hostLiveData

//    // Status Live Data
//    private val _statusLiveData = MutableLiveData<Array<StatusModel>>()
//    val statusLiveData: LiveData<Array<StatusModel>> = _statusLiveData
//
//    // Progs Live Data
//    private val _progsLiveData = MutableLiveData(ProgsModel)
//    val progsLiveData: LiveData<ProgsModel.Companion> = _progsLiveData
//
//    // Disk Live Data
//    private val _diskLiveData = MutableLiveData(DiskModel)
//    val diskLiveData: LiveData<DiskModel.Companion> = _diskLiveData

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

                        _hostLiveData.value = resObject.data.host

//                        _hostLiveData.value.hostname = resObject.data.host.hostname
//                        _hostLiveData.value.os = resObject.data.host.os
//                        _hostLiveData.value.ip = resObject.data.host.ip
//                        _hostLiveData.value.kernel = resObject.data.host.kernel
//                        _hostLiveData.value.uptime = resObject.data.host.uptime
//                        _hostLiveData.value.date = resObject.data.host.date
//                        _hostLiveData.value.updates = resObject.data.host.updates

//                        _statusLiveData.value = resObject.data.status

//                        println(hostLiveData.value)
//                        println(hostLiveData.value.hostname)
//                        println(hostLiveData.value.os)
//                        println(hostLiveData.value.ip)
//                        println(hostLiveData.value.kernel)
                    } else {
                        widgetData.value = "Error"
                    }
                }
            } catch (exception: Exception) {
                widgetData.value = "Error"
            }
        }
    }
}