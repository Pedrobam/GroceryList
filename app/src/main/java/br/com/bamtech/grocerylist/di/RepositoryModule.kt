package br.com.bamtech.grocerylist.di

import br.com.bamtech.grocerylist.data.repository.GroceryRepositoryImpl
import br.com.bamtech.grocerylist.domain.repository.GroceryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        impl: GroceryRepositoryImpl
    ): GroceryRepository
}