package com.example.emergencysystem.util

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.example.emergencysystem.R
import com.example.emergencysystem.util.Screens
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Items(
    val id: Int,
    @DrawableRes val image: Int,
    val text: String,
    val route: String = ""
): Parcelable

val hospital = Items(
    id = 1,
    image = R.drawable.hospital,
    text = "Hospital",
    Screens.MapScreen.route
)

val doctor = Items(
    id = 2,
    image = R.drawable.doctor,
    text = "Doctor",
    Screens.MapScreen.route
)
val ambulance = Items(
    id = 3,
    image = R.drawable.ic_ambulance,
    text = "Ambulance"
)
val contact = Items(
    id = 4,
    image = R.drawable.contact,
    text = "Next of kin",
    Screens.TODO_LIST.route
)

val allItems = listOf(hospital, doctor, ambulance, contact)

