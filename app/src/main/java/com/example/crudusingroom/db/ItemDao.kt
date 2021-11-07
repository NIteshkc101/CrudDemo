package com.example.crudusingroom.db

import androidx.room.*
import com.example.crudusingroom.modal.Items

@Dao
interface ItemDao {
    @Insert
    fun insert(items: Items)

    @Update
    fun update(items: Items)

    @Delete
    fun delete(items: Items)

    @Query("SELECT * FROM items")
    fun getAll() : List<Items>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getById(id: Int) : List<Items>
}