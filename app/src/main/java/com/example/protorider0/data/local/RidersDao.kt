package com.example.protorider0.data.local

import androidx.room.*


@Dao
interface RidersDao {
    @Query("SELECT * FROM riders")
    fun getAll() : List<RiderLocal>

    @Query("SELECT * FROM riders WHERE rider = :rider LIMIT 1")
    fun getByName(rider: String) : RiderLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg  rider: RiderLocal)

    @Delete
    fun delete(rider: RiderLocal)
}