package com.pickupservices.mypics.data.network.service

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for handle API informations
 */
interface AppService {

    companion object {
        const val baseUrl = "https://jsonplaceholder.typicode.com/"
    }

}