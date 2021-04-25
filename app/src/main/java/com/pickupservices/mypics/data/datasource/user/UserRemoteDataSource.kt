package com.pickupservices.mypics.data.datasource.user

import com.pickupservices.mypics.data.network.response.GetAllUsersResponse
import com.pickupservices.mypics.data.network.service.UserService
import javax.inject.Inject

class UserRemoteDataSource  @Inject constructor(private val userService: UserService) {

    suspend fun getAll(): List<GetAllUsersResponse> {
        return userService.getAll()
    }
}