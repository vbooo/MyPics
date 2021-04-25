package com.pickupservices.mypics.di

import com.pickupservices.mypics.data.repository.AlbumRepository
import com.pickupservices.mypics.domain.repository.IAlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MainModule {

    @Singleton
    @Provides
    fun provideAlbumRepository(): IAlbumRepository {
        return AlbumRepository()
    }
}