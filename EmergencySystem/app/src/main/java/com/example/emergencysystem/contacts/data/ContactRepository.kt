package com.example.emergencysystem.contacts.data

import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    suspend fun insert(contact: Contacts)

    suspend fun delete(contact: Contacts)

    suspend fun getContactById(id: Int): Contacts?

    fun getAll(): Flow<List<Contacts>>
}