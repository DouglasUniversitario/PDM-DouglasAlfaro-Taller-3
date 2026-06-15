package com.pdmcourse2026.basictemplate.repository

import com.pdmcourse2026.basictemplate.remote.PlaceDto

interface PlaceRepository {
    suspend fun getPlaces(): Result<List<PlaceDto>>
    suspend fun voteForPlace(placeId: Int, carnet: String): Result<Unit>
}
