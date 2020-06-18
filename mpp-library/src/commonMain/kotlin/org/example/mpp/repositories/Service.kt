package org.example.mpp.repositories

import kotlinx.serialization.json.JsonObject
import org.example.mpp.models.TableRowModel

class Service {
    companion object {
        private lateinit var items: ArrayList<TableRowModel>

        fun collect(jsonObject: JsonObject): ArrayList<TableRowModel> {
            items = ArrayList()
            for (element in jsonObject) {
                val data = element as JsonObject
                val status = data["status"].toString()
//                var icon = R.drawable.ic_empty;
                if (status.toInt() > 0) {
//                    icon = R.drawable.ic_tick
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