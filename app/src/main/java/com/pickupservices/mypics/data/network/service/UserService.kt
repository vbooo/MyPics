package com.pickupservices.mypics.data.network.service

import com.pickupservices.mypics.data.network.response.GetAllUsersResponse
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun getAll(): List<GetAllUsersResponse>
}