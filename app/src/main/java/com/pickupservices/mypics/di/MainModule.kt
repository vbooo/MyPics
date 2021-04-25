package com.pickupservices.mypics.di

import android.content.Context
import com.pickupservices.mypics.data.INetworkUtils
import com.pickupservices.mypics.data.datasource.album.AlbumLocalDataSource
import com.pickupservices.mypics.data.datasource.album.AlbumRemoteDataSource
import com.pickupservices.mypics.data.datasource.user.UserLocalDataSource
import com.pickupservices.mypics.data.datasource.user.UserRemoteDataSource
import com.pickupservices.mypics.data.db.MyPicsDatabase
import com.pickupservices.mypics.data.network.service.AlbumService
import com.pickupservices.mypics.data.network.service.AppService.Companion.baseUrl
import com.pickupservices.mypics.data.network.service.UserService
import com.pickupservices.mypics.data.repository.AlbumRepository
import com.pickupservices.mypics.data.repository.UserRepository
import com.pickupservices.mypics.data.utils.NetworkUtils
import com.pickupservices.mypics.domain.repository.IAlbumRepository
import com.pickupservices.mypics.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MainModule {

    @Singleton
    @Provides
    fun provideNetworkUtils(
        @ApplicationContext context: Context
    ): INetworkUtils {
        return NetworkUtils(context)
    }

    @Singleton
    @Provides
    fun provideAlbumRepository(
        albumLocalDataSource: AlbumLocalDataSource,
        albumRemoteDataSource: AlbumRemoteDataSource,
        networkUtils: INetworkUtils
    ): IAlbumRepository {
        return AlbumRepository(
            albumLocalDataSource,
            albumRemoteDataSource,
            networkUtils
        )
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        userLocalDataSource: UserLocalDataSource,
        userRemoteDataSource: UserRemoteDataSource,
        networkUtils: INetworkUtils
    ): IUserRepository {
        return UserRepository(
            userLocalDataSource,
            userRemoteDataSource,
            networkUtils
        )
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): MyPicsDatabase {
        return MyPicsDatabase.buildDatabase(context)
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesAlbumApi(retrofit: Retrofit): AlbumService = retrofit.create(
        AlbumService::class.java)

    @Provides
    @Singleton
    fun providesUserApi(retrofit: Retrofit): UserService = retrofit.create(
        UserService::class.java)
}