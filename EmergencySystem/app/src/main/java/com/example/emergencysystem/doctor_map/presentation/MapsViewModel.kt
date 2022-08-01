package com.example.emergencysystem.doctor_map.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emergencysystem.doctor_map.domain.model.Doctors
import com.example.emergencysystem.doctor_map.domain.repository.DoctorsSpotRepository
import com.google.android.gms.maps.model.MapStyleOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
     val repository: DoctorsSpotRepository): ViewModel() {
    var state by mutableStateOf(MapScreenState())

    init {
        viewModelScope.launch {
            repository.getAllDoctors().collectLatest { spots ->
                state = state.copy(doctorsSpots = spots)
            }
        }
    }

    fun onEvent(event: MapEvent) {
        when(event) {
            is MapEvent.ToggleFallout -> {
                state = state.copy(
                    properties = state.properties.copy(
                    mapStyleOptions = if(state.isFallout) {
                        null
                    } else MapStyleOptions(MapStyle.json),
                ),isFallout = !state.isFallout)
            }
            is MapEvent.onMapLongClick -> {
                viewModelScope.launch {
                    repository.addDoctors(Doctors(event.latLng.latitude, event.latLng.longitude))
                }
            }
            is MapEvent.OnInfoWindowLongClick -> {
                viewModelScope.launch {
                    repository.deleteDoctors(event.spot)
                }
            }
        }
    }
}