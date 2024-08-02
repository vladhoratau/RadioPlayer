package com.example.radioplayer.corejava;

import com.example.radioplayer.infrajava.RadioStationRepo;
import com.example.radioplayer.infrajava.RepositoryModule;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

/**
 * Module class for providing the usecases of the application.
 */
@Module(includes = {RepositoryModule.class})
@InstallIn(SingletonComponent.class)
public class UseCaseModule {

    /**
     * Method for providing the getRadioStationsByCountryCode use case.
     *
     * @param radioStationRepo radioStation Repo object
     * @return the object representing an instance of GetRadioStationsByCountryCodeUseCase class
     */
    @Provides
    @Singleton
    public GetRadioStationsByCountryCodeUseCase provideGetRadioStationByCountryCodeUseCase(RadioStationRepo radioStationRepo) {
        return new GetRadioStationsByCountryCodeUseCase(radioStationRepo);
    }
}
