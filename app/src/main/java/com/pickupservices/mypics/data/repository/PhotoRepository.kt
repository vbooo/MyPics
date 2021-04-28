package com.pickupservices.mypics.data.repository

import com.pickupservices.mypics.data.INetworkUtils
import com.pickupservices.mypics.data.datasource.photo.PhotoLocalDataSource
import com.pickupservices.mypics.data.datasource.photo.PhotoRemoteDataSource
import com.pickupservices.mypics.domain.Result
import com.pickupservices.mypics.domain.model.Photo
import com.pickupservices.mypics.domain.repository.IPhotoRepository
import timber.log.Timber
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val datasourcePhotoLocal: PhotoLocalDataSource,
    private val datasourcePhotoRemote: PhotoRemoteDataSource,
    private val networkUtils: INetworkUtils
): IPhotoRepository {

    override suspend fun getPhotosByAlbum(id: Int): Result<List<Photo>?> {
        return try {
            val response = datasourcePhotoLocal.getPhotosByAlbum(id)
            Result.Success(response)
        } catch (e: Exception) {
            Timber.e("Error while get photos for album id $id: ${e.message}")
            Result.Error(Exception(e))
        }
    }

    override suspend fun refreshData() {
        if (networkUtils.isConnected()) {
            // get remote data if device is online
            val response = datasourcePhotoRemote.getAll()
            // save data in local DB for offline usage
            datasourcePhotoLocal.savePhotos(response)
        } else {
            Timber.i("Refresh photos data is impossible because no Internet connection")
        }
    }
}
