package net.compoza.lite2.mpp.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header

class CompozaApi {
    suspend fun getStatusServer(host: String, path: String, token: String): String {
        val client = HttpClient()
        return client.get("${host}${path}") {
            header("Authorization", "Bearer $token")
        }
    }

}
