//package org.example.mpp.api
//
//import io.ktor.client.HttpClient
//import io.ktor.client.request.get
//import io.ktor.client.request.url
//import io.ktor.http.auth.parseAuthorizationHeader
//
//class CompozaApi() {
//    private val client = HttpClient()
//    suspend fun getStatusServer(): String {
//        return client.get {
//            url(apiUrl)
//            parseAuthorizationHeader(apiToken)
//        }
//    }
//
//    companion object {
//        private const val apiUrl = "https://xray.instamaven.com/api/info/"
//        private const val apiToken = "aJK8YL6bxv6PyfqT8VZX6MIbpIKSiQZAm0qk5molxjwe65RwQJI1BzSmCuBzKKvKbbCsbZY90Yqw0cZ3fWR3YzEoZVFVNKU3gw7OrjS3beVh6iCxwboWKT1ZNbvkiAaQlA0uogYFGY5tJYRr6OCDR0"
//    }
//}
