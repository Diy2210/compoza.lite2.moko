package org.example.mpp.repositories

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import org.example.mpp.models.TableRowModel

@Serializable
class ServiceInfo {
    companion object {
        private lateinit var items: ArrayList<TableRowModel>

        fun collect(jsonObject: JsonObject): ArrayList<TableRowModel> {
            items = ArrayList()
            for (element in jsonObject) {
                val data = element as JsonObject
                val status = data["status"].toString()
//                var icon = R.drawable.ic_empty;
                if (status.toInt() > 0) {
//                    icon = MR.images.ic_tick
                }
                items.add(
                    TableRowModel(
                        data["name"].toString(), "", 0
                    )
                )
            }
            return items
        }
    }
}