package org.example.mpp.repositories

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import org.example.mpp.models.TableRowModel

@Serializable
class SoftwareInfo {
    companion object {
        private lateinit var items: ArrayList<TableRowModel>

        fun collect(jsonObject: JsonObject): ArrayList<TableRowModel> {
            items = ArrayList()
            for (element in jsonObject) {
                val data = element as JsonObject
                items.add(
                    TableRowModel(
                        data[0.toString()].toString(),
                        data[1.toString()].toString()
                    )
                )
            }
            return items
        }
    }
}