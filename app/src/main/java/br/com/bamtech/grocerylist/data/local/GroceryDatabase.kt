package br.com.bamtech.grocerylist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [GroceryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GroceryDatabase: RoomDatabase() {

    abstract val groceryDao: GroceryDao
}