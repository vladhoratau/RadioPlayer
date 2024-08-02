package com.example.radioplayer.infrajava;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

/** Repository module for providing dependencies */
@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    /**
     * Method for providing the radio station repository
     *
     * @param radioStationService instance of RadioStationService class
     * @return the object representing an instance of RadioStationRepoImpl class
     */
    @Singleton
    @Provides
    public static RadioStationRepo provideRadioStationRepo(RadioStationService radioStationService) {
        return new RadioStationsRepoImpl(radioStationService);
    }
}