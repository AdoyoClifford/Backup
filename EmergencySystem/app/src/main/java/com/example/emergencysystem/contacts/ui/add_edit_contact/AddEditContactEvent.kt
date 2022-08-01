package com.example.emergencysystem.contacts.ui.add_edit_contact

sealed class AddEditContactEvent {
    data class OnTitleChange(val title: String): AddEditContactEvent()
    data class OnDescriptionChange(val description: String): AddEditContactEvent()
    object OnSaveTodoClick: AddEditContactEvent()
}
