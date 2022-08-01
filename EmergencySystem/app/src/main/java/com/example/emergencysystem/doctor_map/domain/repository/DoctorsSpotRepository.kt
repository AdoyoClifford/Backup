package com.example.emergencysystem.doctor_map.domain.repository

import com.example.emergencysystem.doctor_map.domain.model.Doctors
import kotlinx.coroutines.flow.Flow
interface DoctorsSpotRepository {
    suspend fun addDoctors(spot: Doctors)

    suspend fun deleteDoctors(spot: Doctors)

    fun getAllDoctors(): Flow<List<Doctors>>
}