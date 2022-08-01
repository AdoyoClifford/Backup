package com.example.emergencysystem.doctor_map.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DoctorsEntity::class], version = 2)
abstract class DoctorsDataBase: RoomDatabase() {
    abstract val dao: DoctorsDao
}