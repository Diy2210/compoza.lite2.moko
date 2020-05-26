package org.example.mpp.models

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.example.mpp.Server

class ServerViewModel: ViewModel() {
    private val _servers: MutableLiveData<List<Server>> =
        MutableLiveData(
            initialValue = List(10) {
                Server(
                    id = it,
                    title = "Test",
                    url = "test.com"
                )
            }
        )
    val servers: LiveData<List<Server>> = _servers
}