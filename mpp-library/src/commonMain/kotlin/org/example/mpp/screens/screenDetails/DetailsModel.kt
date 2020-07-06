package org.example.mpp.screens.screenDetails

import com.russhwolf.settings.Settings
import com.russhwolf.settings.invoke
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import dev.icerock.moko.units.TableUnitItem
import kotlinx.coroutines.launch
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.example.mpp.api.CompozaApi
import org.example.mpp.models.*
import org.example.mpp.screens.screenServerList.ServerItem

@OptIn(UnstableDefault::class)
@ImplicitReflectionSerializer
class DetailsModel : ViewModel() {
    val settings: Settings = Settings()
    var id = settings.getInt("Server ID")
    var title = settings.getString("Server Title")
    var url = settings.getString("Server Url")
    var token = settings.getString("Server Token")

    private val client = CompozaApi()

    // Status Mutable Live Data
    private val _statusMutableLiveData = MutableLiveData(
        arrayOf(
            StatusModel(
                "Loading",
                "Loading",
                "Loading",
                "Loading"
            )
        )
    )

    // Host Mutable Live Data
    private val _hostMutableLiveData = MutableLiveData(
        HostModel(
            "Loading",
            "Loading",
            "Loading",
            "Loading",
            "Loading",
            0,
            "Loading"
        )
    )

    private val _updates = MutableLiveData("Loading")

    // Host Live Data
    val hostname: LiveData<StringDesc> = _hostMutableLiveData.map { it.hostname.desc() }
    val os: LiveData<StringDesc> = _hostMutableLiveData.map { it.os.desc() }
    val ip: LiveData<StringDesc> = _hostMutableLiveData.map { it.ip.desc() }
    val kernel: LiveData<StringDesc> = _hostMutableLiveData.map { it.kernel.desc() }
    val uptime: LiveData<StringDesc> = _hostMutableLiveData.map { it.uptime.desc() }
    val date: LiveData<StringDesc> = _hostMutableLiveData.map { it.date.desc() }
    val updates: LiveData<StringDesc> = _updates.map { it.desc() }

    // Progs Mutable Live Data
    private val _progsMutableLiveData = MutableLiveData(
        arrayOf(
            ProgsModel(
                "Loading",
                "Loading"
            )
        )
    )

    // Disk Mutable Live Data
//    private val _diskMutableLiveData = MutableLiveData(
//        listOf(
//            DiskModel(
//                "Loading",
//                "Loading",
//                "Loading",
//                "Loading",
//                "Loading",
//                "Loading",
//                "Loading"
//            )
//        )
//    )

    private val _diskMutableLiveData: MutableLiveData<List<DiskModel>> =
        MutableLiveData(initialValue = List(1) {
                DiskModel(
                "Loading",
                "Loading",
                "Loading",
                "Loading",
                "Loading",
                "Loading",
                "Loading"
            )
        }
        )

    val statusInfoArray: LiveData<Array<StatusModel>> = _statusMutableLiveData
    val progsInfoArray: LiveData<Array<ProgsModel>> = _progsMutableLiveData
    val diskInfoList: MutableLiveData<List<DiskModel>> = _diskMutableLiveData

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

                        // Status Value
                        _statusMutableLiveData.value = resObject.data.status
                        println("//////////STATUS-" + _statusMutableLiveData.value[0])
                        println("//////////STATUS-" + _statusMutableLiveData.value[1])

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

                        // Progs Value
                        _progsMutableLiveData.value = resObject.data.progs
                        println("//////////PROGS-" + _progsMutableLiveData.value[0])
                        println("//////////PROGS-" + _progsMutableLiveData.value[1])

                        // Disk Value
                        _diskMutableLiveData.value = resObject.data.disk_fs.asList()
                        println("//////////DISK-" + _diskMutableLiveData.value[0])
                        println("//////////DISK-" + _diskMutableLiveData.value[1])
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