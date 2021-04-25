package com.pickupservices.mypics.domain.repository

import com.pickupservices.mypics.domain.Result
import com.pickupservices.mypics.domain.model.User

interface IUserRepository {
    suspend fun getUserById(id: Int): Result<User>
    suspend fun refreshUserData()
}