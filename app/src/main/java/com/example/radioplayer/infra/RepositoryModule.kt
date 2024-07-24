package com.example.radioplayer.infra

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /** Method for providing the radio station repository
     * @param radioStationService instance of RadioStationService class
     * @return the object representing an instance of RadioStationRepoImpl class */
    @Singleton
    @Provides
    fun provideRadioStationRepo(radioStationService: RadioStationService): RadioStationRepo {
        return RadioStationsRepoImpl(radioStationService)
    }
}