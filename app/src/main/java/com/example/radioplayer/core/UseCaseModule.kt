//package com.example.radioplayer.core
//
//import com.example.radioplayer.infra.RadioStationRepo
//import com.example.radioplayer.infra.RepositoryModule
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//
///** Module class for providing the usecases of the application */
//@Module(includes = [RepositoryModule::class])
//@InstallIn(SingletonComponent::class)
//object UseCaseModule {
//
//
//    /** Method for providing the getMoviesByName use case *
//     * @param radioStationRepo radioStation Repo object
//     * @return the object representing an instance of GetRadioStationsByCountryCodeUseCase class*/
//    @Singleton
//    @Provides
//    fun provideGetRadioStationByCountryCodeUseCase(radioStationRepo: RadioStationRepo): GetRadioStationsByCountryCodeUseCase {
//        return GetRadioStationsByCountryCodeUseCase(radioStationRepo)
//    }
//}