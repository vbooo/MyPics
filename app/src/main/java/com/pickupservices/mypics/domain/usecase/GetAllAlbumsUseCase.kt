package com.pickupservices.mypics.domain.usecase

import com.pickupservices.mypics.domain.model.Album
import com.pickupservices.mypics.domain.repository.IAlbumRepository
import kotlinx.coroutines.CoroutineDispatcher
import com.pickupservices.mypics.domain.IoDispatcher
import javax.inject.Inject

class GetAllAlbumsUseCase  @Inject constructor (
    private val repository: IAlbumRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<Unit, List<Album>>(dispatcher) {

    override suspend fun execute(parameters: Unit): List<Album> {
        return repository.getAllAlbums()
    }

}