package br.com.bamtech.grocerylist.di

import br.com.bamtech.grocerylist.data.repository.GroceryRepositoryImpl
import br.com.bamtech.grocerylist.domain.repository.GroceryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(
        impl: GroceryRepositoryImpl
    ): GroceryRepository
}