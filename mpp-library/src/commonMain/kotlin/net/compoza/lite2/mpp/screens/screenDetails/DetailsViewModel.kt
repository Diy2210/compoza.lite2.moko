package net.compoza.lite2.mpp.screens.screenDetails

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
import net.compoza.lite2.library.MR
import net.compoza.lite2.mpp.api.CompozaApi
import net.compoza.lite2.mpp.models.*

@OptIn(UnstableDefault::class)
@ImplicitReflectionSerializer
class DetailsViewModel : ViewModel() {

    private val client = CompozaApi()

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

    // Disk Mutable Live Data
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
        })

    // Status Mutable Live Data
    private val _statusMutableLiveData: MutableLiveData<List<StatusModel>> =
        MutableLiveData(initialValue = List(1) {
            StatusModel(
                "Loading",
                "Loading",
                "Loading",
                "Loading"
            )
        })

    // Progs Mutable Live Data
    private val _progsMutableLiveData: MutableLiveData<List<ProgsModel>> =
        MutableLiveData(initialValue = List(1) {
            ProgsModel(
                "Loading",
                "Loading"
            )
        })

    val diskInfoList: MutableLiveData<List<DiskModel>> = _diskMutableLiveData
    val statusInfoArray: MutableLiveData<List<StatusModel>> = _statusMutableLiveData
    val progsInfoArray: MutableLiveData<List<ProgsModel>> = _progsMutableLiveData

    init {
        launchAsyncRequest()
    }

    @UnstableDefault
    @ImplicitReflectionSerializer
    fun launchAsyncRequest() {
        viewModelScope.launch {
            try {
                client.getStatusServer(MR.strings.url.toString(), "/api/v1.1/info", MR.strings.token.toString())
                    .also { response ->
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

                            // Disk Value
                            _diskMutableLiveData.value = resObject.data.disk_fs.asList()

                            // Status Value
                            _statusMutableLiveData.value = resObject.data.status.asList()

                            // Progs Value
                            _progsMutableLiveData.value = resObject.data.progs.asList()
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