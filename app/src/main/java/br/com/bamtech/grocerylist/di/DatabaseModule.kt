package br.com.bamtech.grocerylist.di

import android.app.Application
import androidx.room.Room
import br.com.bamtech.grocerylist.data.local.GroceryDao
import br.com.bamtech.grocerylist.data.local.GroceryDatabase
import br.com.bamtech.grocerylist.data.repository.GroceryRepositoryImpl
import br.com.bamtech.grocerylist.domain.repository.GroceryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGroceryDatabase(app: Application): GroceryDatabase {
        return Room.databaseBuilder(
            app,
            GroceryDatabase::class.java,
            "grocery_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGroceryDao(db: GroceryDatabase) = db.groceryDao

    @Provides
    fun provideGroceryRepository(dao: GroceryDao): GroceryRepository = GroceryRepositoryImpl(dao)
}