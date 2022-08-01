package com.example.emergencysystem.doctor_map.data

import com.example.emergencysystem.doctor_map.domain.model.Doctors
import com.example.emergencysystem.doctor_map.domain.repository.DoctorsSpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DoctorsRepositoryImpl(private val dao: DoctorsDao): DoctorsSpotRepository {
    override suspend fun addDoctors(spot: Doctors) {
        dao.insertSpot(spot.toDoctorsEntity())
    }

    override suspend fun deleteDoctors(spot: Doctors) {
        dao.deleteSpot(spot.toDoctorsEntity())
    }

    override fun getAllDoctors(): Flow<List<Doctors>> {
        return dao.getParkingSpots().map { spots ->
            spots.map { it.toDoctors() }
        }
    }


}