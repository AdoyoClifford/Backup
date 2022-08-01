package com.example.emergencysystem.contacts.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contacts)

    @Delete
    suspend fun deleteContact(contact: Contacts)

    @Query("SELECT * FROM contacts WHERE id = :id")
    suspend fun getContactById(id: Int): Contacts

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<Contacts>>
}