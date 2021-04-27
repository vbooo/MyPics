package com.pickupservices.mypics.domain.usecase

import com.pickupservices.mypics.domain.IoDispatcher
import com.pickupservices.mypics.domain.Result
import com.pickupservices.mypics.domain.data
import com.pickupservices.mypics.domain.repository.IAlbumRepository
import com.pickupservices.mypics.domain.repository.IUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllAlbumsUseCase  @Inject constructor (
    private val albumRepository: IAlbumRepository,
    private val userRepository: IUserRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): FlowUseCase<Unit, List<FunctionalAlbum>>(dispatcher) {

    override fun execute(parameters: Unit): Flow<Result<List<FunctionalAlbum>>> = flow {
        // Work is starting
        emit(Result.Loading)

        // First, we refresh the data from the remote server
        userRepository.refreshData()
        albumRepository.refreshData()

        // Instanciating the list of functional Album to return
        val listFunctionalAlbum: MutableList<FunctionalAlbum> = mutableListOf()

        albumRepository.getAllAlbums().let {
            for (album in it.data.orEmpty()) {
                listFunctionalAlbum.add(
                    FunctionalAlbum(
                        album.id,
                        album.title,
                        userRepository.getUserById(
                            album.idAuthor
                        ).data?.name
                    )
                )
            }
        }

        emit(Result.Success(listFunctionalAlbum))
    }


}

data class FunctionalAlbum (
    /**
     * Id of the album
     */
    val id: Int,

    /**
     * Name album value
     */
    val nameAlbum: String,

    /**
     * Name of the album author
     */
    val nameAuthor: String?
)