package com.app.punkapp.di

import com.app.data.Repository
import com.app.punkapp.model.BeerInteractor
import com.app.usescases.BeerUsesCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object BeerModuleInteractor {

    @Provides
    @ViewModelScoped
    fun beerUsesCases(
        beerUsesCases: BeerUsesCases
    ): BeerInteractor {
        return BeerInteractor(
            beerUsesCases
        )
    }
}