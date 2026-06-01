package com.pdmcourse2026.basictemplate.repository


import com.pdmcourse2026.basictemplate.data.api.PlaceDto
import com.pdmcourse2026.basictemplate.model.VoteRequest
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class PlaceRepositoryImpl(private val client: HttpClient) : PlaceRepository {


    private val baseUrl = "https://qjcxdvfzyseuvezacxsd.supabase.co/functions/v1/rankeuca/"
    private val apiKey = "4dc8f785-8f2d-4911-a4a5-0fda9fd9317c"

    override suspend fun getPlaces(): Result<List<PlaceDto>> {
        return try {
            val response = client.get("${baseUrl}options") {
                header(HttpHeaders.Authorization, "Bearer $apiKey")
            }
            if (response.status.isSuccess()) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("Error al obtener lugares: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun voteForPlace(placeId: Int): Result<Unit> {
        return try {
            val response = client.post("${baseUrl}register") {
                header(HttpHeaders.Authorization, "Bearer $apiKey")
                contentType(ContentType.Application.Json)
                setBody(VoteRequest(placeId))
            }
            if (response.status.isSuccess()) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error al votar: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}