package com.example.emergencysystem.doctor_map.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DoctorsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpot(spot: DoctorsEntity)

    @Delete
    suspend fun deleteSpot(spot: DoctorsEntity)

    @Query("SELECT * FROM DoctorsEntity")
    fun getParkingSpots(): Flow<List<DoctorsEntity>>
}
