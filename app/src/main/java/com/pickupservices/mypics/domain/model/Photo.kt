package com.pickupservices.mypics.domain.model

data class Photo(
    /**
     * id
     */
    val id: Int,

    /**
     * albumId
     */
    val albumId: Int,

    /**
     * thumbnailUrl
     */
    val thumbnailUrl: String
)
