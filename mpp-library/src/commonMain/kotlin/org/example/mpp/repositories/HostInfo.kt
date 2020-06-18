package org.example.mpp.repositories

import kotlinx.serialization.Serializable
import org.example.mpp.models.TableRowModel
import kotlinx.serialization.json.*

@Serializable
class HostInfo {
    companion object {
        private lateinit var items: ArrayList<TableRowModel>

        fun collect(jsonObject: JsonObject): ArrayList<TableRowModel> {
            items = ArrayList()
            items.add(
                TableRowModel(
//                    MR.strings.system_hostname.toString(),
                    "System Hostname",
                    jsonObject["hostname"].toString()
//                    hostname = jsonObject["hostname"].toString()
                )
            )
            items.add(
                TableRowModel(
//                    MR.strings.system_os.toString(),
                    "System OS",
                    jsonObject["os"].toString()
//                      os = jsonObject["os"].toString()
                )
            )
            items.add(
                TableRowModel(
//                    MR.strings.public_ip.toString(),
                    "IP",
                    jsonObject["ip"].toString()
//                      ip = jsonObject["ip"].toString()

                )
            )
            items.add(
                TableRowModel(
//                    MR.strings.kernel_cpu.toString(),
                    "Kernel",
                    jsonObject["kernel"].toString()
//                    kernel = jsonObject["kernel"].toString()
                )
            )
            items.add(
                TableRowModel(
//                    MR.strings.system_uptime.toString(),
                    "System Uptime",
                    jsonObject["uptime"].toString()
//                    uptime = jsonObject["uptime"].toString()
                )
            )
            items.add(
                TableRowModel(
//                    MR.strings.system_date.toString(),
                    "System Date",
                    jsonObject["date"].toString()
//                    date = jsonObject["date"].toString()
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