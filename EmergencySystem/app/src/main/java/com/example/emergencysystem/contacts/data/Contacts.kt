package com.example.emergencysystem.contacts.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contacts(
    val name: String,
    val phone: String?,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null
)
