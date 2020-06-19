package org.example.mpp.models

import kotlinx.serialization.Serializable

@Serializable
data class DataModel (
    val status: Array<StatusModel>,
    val host: HostModel,
    val progs: Array<ProgsModel>,
    val disk_fs: Array<DiskModel>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DataModel

        if (!status.contentEquals(other.status)) return false
        if (host != other.host) return false
        if (!progs.contentEquals(other.progs)) return false
        if (!disk_fs.contentEquals(other.disk_fs)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status.contentHashCode()
        result = 31 * result + host.hashCode()
        result = 31 * result + progs.contentHashCode()
        result = 31 * result + disk_fs.contentHashCode()
        return result
    }
}