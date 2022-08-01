package com.example.emergencysystem.doctor_map.presentation

import com.example.emergencysystem.doctor_map.domain.model.Doctors
import com.google.android.gms.maps.model.LatLng

sealed class MapEvent{
    object ToggleFallout: MapEvent()
    data class onMapLongClick(val latLng: LatLng): MapEvent()
    data class OnInfoWindowLongClick(val spot: Doctors): MapEvent()


}
