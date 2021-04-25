package com.pickupservices.mypics.data.repository

import com.pickupservices.mypics.data.INetworkUtils
import com.pickupservices.mypics.data.datasource.user.UserLocalDataSource
import com.pickupservices.mypics.data.datasource.user.UserRemoteDataSource
import com.pickupservices.mypics.data.network.response.GetAllUsersResponse
import com.pickupservices.mypics.domain.Result
import com.pickupservices.mypics.domain.model.User
import com.pickupservices.mypics.domain.repository.IUserRepository
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val datasourceUserLocal: UserLocalDataSource,
    private val datasourceUserRemote: UserRemoteDataSource,
    private val networkUtils: INetworkUtils
): IUserRepository {

    override suspend fun getUserById(id: Int): Result<User> {

        return try {
            Result.Success(datasourceUserLocal.getUserById(id))
        } catch (e: Exception) {
            Timber.e("Error while get user by id: ${e.message}")
            Result.Error(Exception(e))
        }
    }

    override suspend fun refreshUserData() {
        if (networkUtils.isConnected()) {
            // we first get the fresh user data
            val allUsersResponse = datasourceUserRemote.getAll()
            // then we save it locally
            datasourceUserLocal.saveUsers(allUsersResponse)
        }
    }

}
