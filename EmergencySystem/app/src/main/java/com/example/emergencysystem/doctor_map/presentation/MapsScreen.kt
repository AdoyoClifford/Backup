package com.example.emergencysystem.doctor_map.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.ramcosta.composedestinations.annotation.Destination

@Composable
fun MapScreen(navController: NavController,id: Int?,) {
    val viewModel: MapsViewModel = hiltViewModel()
    val cameraPositionState1 = rememberCameraPositionState()
    val scaffoldState = rememberScaffoldState()
    val uiSettings =remember{MapUiSettings(zoomControlsEnabled = false)}

   // val maps = id.let{it1 -> DocRepository.findDoc(it1)}

  //  val merkerPosition = LatLng(viewModel.latitude, viewModel.longitude)



    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          viewModel.onEvent(MapEvent.ToggleFallout)
                },) {
                Icon(imageVector = if(viewModel.state.isFallout) {
                    Icons.Default.ToggleOn
                } else Icons.Default.ToggleOff, contentDescription ="Toggle Fallout" )
            }
        }
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
            onMapLongClick = {
                viewModel.onEvent(MapEvent.onMapLongClick(it))
            },
            cameraPositionState = CameraPositionState(
                CameraPosition(
                    LatLng(
                        1.35,
                        103.87
                    ),12f,0f,0f
                )
            )
        ) {
            viewModel.state.doctorsSpots.forEach { spot ->
                Marker(
                   position = LatLng(spot.lat, spot.lng),
                    //state =  MarkerState(position = singapore),
                    title = "Doctor's location (${spot.lat}, ${spot.lng})",
                    snippet = "Long click to delete",
                    onInfoWindowLongClick = {
                        viewModel.onEvent(
                            MapEvent.OnInfoWindowLongClick(spot)
                        )
                    },
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_GREEN
                    )
                )
            }
        }

    }
}