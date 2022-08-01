package com.example.emergencysystem.doctor_map.presentation

import com.example.emergencysystem.doctor_map.domain.model.Doctors
import com.google.maps.android.compose.MapProperties

data class MapScreenState(
    val properties: MapProperties = MapProperties(),
    val doctorsSpots: List<Doctors> = emptyList(),
    val isFallout: Boolean = false,
)
