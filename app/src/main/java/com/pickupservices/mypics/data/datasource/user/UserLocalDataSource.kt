package com.pickupservices.mypics.data.datasource.user

import com.pickupservices.mypics.data.db.MyPicsDatabase
import com.pickupservices.mypics.data.db.entity.AlbumEntity
import com.pickupservices.mypics.data.db.entity.UserEntity
import com.pickupservices.mypics.data.network.response.GetAllAlbumsResponse
import com.pickupservices.mypics.data.network.response.GetAllUsersResponse
import com.pickupservices.mypics.domain.model.User
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val appDatabase: MyPicsDatabase
)
{
    fun saveUsers(listUser: List<GetAllUsersResponse>) {
        for (user in listUser) {
            appDatabase.userDao().insert(
                user = UserEntity(
                user.id,
                user.name
                )
            )
        }
    }

    fun getUserById(id: Int): User {
        val user = appDatabase.userDao().getById(id)
        return User(
            user.id,
            user.name
        )
    }
}