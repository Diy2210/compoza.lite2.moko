package org.example.mpp.repositories

import org.example.mpp.models.TableRowModel
import kotlinx.serialization.json.*
import org.example.library.MR

class Host {
    companion object {
        private lateinit var items: ArrayList<TableRowModel>

        fun collect(jsonObject: JsonObject): ArrayList<TableRowModel> {
            items = ArrayList()
            items.add(
                TableRowModel(
                    MR.strings.system_hostname.toString(),
                    jsonObject.get("hostname").toString()
                )
            )
            items.add(
                TableRowModel(
                    MR.strings.system_os.toString(),
                    jsonObject.get("os").toString()
                )
            )
            items.add(
                TableRowModel(
                    MR.strings.public_ip.toString(),
                    jsonObject.get("ip").toString()
                )
            )
            items.add(
                TableRowModel(
                    MR.strings.kernel_cpu.toString(),
                    jsonObject.get("kernel").toString()
                )
            )
            items.add(
                TableRowModel(
                    MR.strings.system_uptime.toString(),
                    jsonObject.get("uptime").toString()
                )
            )
            items.add(
                TableRowModel(
                    MR.strings.system_date.toString(),
                    jsonObject.get("date").toString()
                )
            )
//            val updates = JsonPrimitive("updates").toString()
//            if (updates.toInt() > 0) {
//                items.add(
//                    TableRowModel(
//                        MR.strings.system_updates.toString(),
//                        MR.strings.updates_total.toString(), updates),
//                        R.drawable.ic_alert
//                    )
//                )
//            }
            return items
        }
    }
}