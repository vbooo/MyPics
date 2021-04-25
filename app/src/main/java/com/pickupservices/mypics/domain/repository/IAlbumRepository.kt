package com.pickupservices.mypics.domain.repository

import com.pickupservices.mypics.domain.model.Album

interface IAlbumRepository {
    fun getAllAlbums(): List<Album>
}