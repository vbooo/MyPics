package com.pickupservices.mypics.data.network.response

data class GetAllPhotosResponse(
    val id: Int,
    val albumId: Int,
    val thumbnailUrl: String
)
