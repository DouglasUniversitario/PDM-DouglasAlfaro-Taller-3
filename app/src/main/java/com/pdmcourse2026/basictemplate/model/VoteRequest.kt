package com.pdmcourse2026.basictemplate.model

import kotlinx.serialization.Serializable

@Serializable
data class VoteRequest(
    val placeId: Int
)