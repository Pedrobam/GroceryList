package br.com.bamtech.grocerylist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryDao {

    @Query("SELECT * FROM grocery_items")
    fun observeItems(): Flow<List<GroceryEntity>>

    @Query("SELECT * FROM grocery_items WHERE id = :id")
    suspend fun getItemById(id: Long): GroceryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: GroceryEntity)

    @Delete
    suspend fun delete(item: GroceryEntity)

    @Update
    suspend fun update(item: GroceryEntity)
}