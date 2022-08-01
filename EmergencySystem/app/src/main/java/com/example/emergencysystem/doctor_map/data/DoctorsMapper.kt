package com.example.emergencysystem.doctor_map.data

import com.example.emergencysystem.doctor_map.domain.model.Doctors

fun DoctorsEntity.toDoctors(): Doctors {
    return Doctors(
        lat = lat,
        lng = lng,
        id = id,
    )
}

fun Doctors.toDoctorsEntity(): DoctorsEntity {
    return DoctorsEntity(
        lat = lat,
        lng = lng,
        id = id,
    )
}