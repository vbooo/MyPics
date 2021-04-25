package com.pickupservices.mypics.data.repository

import com.pickupservices.mypics.data.INetworkUtils
import com.pickupservices.mypics.data.datasource.album.AlbumLocalDataSource
import com.pickupservices.mypics.data.datasource.album.AlbumRemoteDataSource
import com.pickupservices.mypics.domain.Result
import com.pickupservices.mypics.domain.model.Album
import com.pickupservices.mypics.domain.repository.IAlbumRepository
import timber.log.Timber
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val datasourceAlbumLocal: AlbumLocalDataSource,
    private val datasourceAlbumRemote: AlbumRemoteDataSource,
    private val networkUtils: INetworkUtils
): IAlbumRepository {

    override suspend fun getAllAlbums(): Result<List<Album>?> {
        val allAlbums = mutableListOf<Album>()

            return try {
                if (networkUtils.isConnected()) {
                    // get remote data if device is online
                    val response = datasourceAlbumRemote.getAll()

                    // save data in local DB for offline usage
                    allAlbums.let {
                        datasourceAlbumLocal.saveAlbums(response)
                    }

                    // fill the album list to return
                    for (album in response) {
                        allAlbums.add(
                            Album(
                                album.id,
                                album.title,
                                album.userId
                            )
                        )
                    }
                    Result.Success(allAlbums)
                } else {
                    Result.Success(allAlbums)
                }
            } catch (e: Exception) {
                Timber.e("Error while get all remote albums: ${e.message}")
                Result.Error(Exception(e))
            }

    }
}
