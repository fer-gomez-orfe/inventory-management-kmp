package org.montra.crudmuliplatform.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.montra.crudmuliplatform.data.model.SparePartModel
import org.montra.crudmuliplatform.getPlatform

class SparePartService (private val httpClient: HttpClient) {

    suspend fun getSpareParts(): List<SparePartModel> {
        val baseUrl = getPlatform().hostUrl
        return httpClient.get("$baseUrl/spare_parts/").body()
    }

}