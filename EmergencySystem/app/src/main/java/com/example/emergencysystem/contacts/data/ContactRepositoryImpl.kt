package com.example.emergencysystem.contacts.data

import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl(
    private val dao: ContactDao
): ContactRepository {
    override suspend fun insert(contact: Contacts) {
        dao.insertContact(contact)
    }

    override suspend fun delete(contact: Contacts) {
        dao.deleteContact(contact)
    }

    override suspend fun getContactById(id: Int): Contacts? {
        return dao.getContactById(id)
    }

    override fun getAll(): Flow<List<Contacts>> {
        return dao.getAllContacts()
    }

}