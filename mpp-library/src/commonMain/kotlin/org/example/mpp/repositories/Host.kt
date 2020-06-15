package org.example.mpp.repositories

import org.example.mpp.models.TableRowModel
import kotlinx.serialization.json.*
import org.example.library.MR

class Host {
    companion object {
        private lateinit var items: ArrayList<TableRowModel>

        fun collect(json: JsonPrimitive): ArrayList<TableRowModel> {
            items = ArrayList()
            items.add(
                TableRowModel(
                    MR.strings.system_hostname.toString(),
                    JsonPrimitive("hostname").toString()
                )
            )
            items.add(
                TableRowModel(
                    MR.strings.system_os.toString(),
                    JsonPrimitive("os").toString()
                )
            )
            items.add(
                TableRowModel(
                    MR.strings.public_ip.toString(),
                    JsonPrimitive("ip").toString()
                )
            )
            items.add(
                TableRowModel(
                    MR.strings.kernel_cpu.toString(),
                    JsonPrimitive("kernel").toString()
                )
            )
            items.add(
                TableRowModel(
                    MR.strings.system_uptime.toString(),
                    JsonPrimitive("uptime").toString()
                )
            )
            items.add(
                TableRowModel(
                    MR.strings.system_date.toString(),
                    JsonPrimitive("date").toString()
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