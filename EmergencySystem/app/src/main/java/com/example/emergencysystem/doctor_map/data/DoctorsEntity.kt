package com.example.emergencysystem.doctor_map.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DoctorsEntity(
    val lat: Double,
    val lng: Double,
    @PrimaryKey val id: Int? = null
)
