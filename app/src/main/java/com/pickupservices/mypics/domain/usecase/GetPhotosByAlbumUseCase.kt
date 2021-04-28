package com.pickupservices.mypics.domain.usecase

import com.pickupservices.mypics.domain.IoDispatcher
import com.pickupservices.mypics.domain.Result
import com.pickupservices.mypics.domain.model.Photo
import com.pickupservices.mypics.domain.repository.IPhotoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPhotosByAlbumUseCase  @Inject constructor (
    private val photoRepository: IPhotoRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): FlowUseCase<Int, List<Photo>>(dispatcher) {

    override fun execute(idAlbum: Int): Flow<Result<List<Photo>>> = flow {
        // Work is starting
        emit(Result.Loading)

        // Refresh the photos data
        photoRepository.refreshData()

        // Get all the photos for corresponding album id
        val response = photoRepository.getPhotosByAlbum(idAlbum)
        if (response is Result.Success) {
            emit(Result.Success(response.data))
        } else {
            emit(Result.Error(Exception("Error")))
        }

    }
}
