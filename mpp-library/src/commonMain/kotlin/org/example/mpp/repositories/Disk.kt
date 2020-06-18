package org.example.mpp.repositories

import kotlinx.serialization.json.JsonObject
import org.example.mpp.models.TableRowModel

class Disk {
    companion object {
        private lateinit var items: ArrayList<TableRowModel>

        fun collect(jsonObject: JsonObject): ArrayList<TableRowModel> {
            items = ArrayList()
            for (element in jsonObject) {
                val data = element as JsonObject

                val total = data["total"].toString().toLong()
                val totalSize = total.toString()
                val free = data["free"].toString().toLong()
                val freeSize = free
                val freePerCents = (free * 100) / total
                items.add(
                    TableRowModel(
                        title = data["dir"].toString(),
                        value = freePerCents.toString() + freeSize.toString(),
                        options = totalSize
                    )
                )
            }
            return items
        }
    }
}