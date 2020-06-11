package org.example.mpp.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.auth.parseAuthorizationHeader

class CompozaApi {
    suspend fun getStatusServer(host: String, path: String, token: String): String {
        val client = HttpClient()
        return client.get("${host}${path}") {
            header("Authorization", "Bearer $token")
        }
    }
}
