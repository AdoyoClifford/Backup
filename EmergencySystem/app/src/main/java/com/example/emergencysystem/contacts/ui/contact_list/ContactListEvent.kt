package com.example.emergencysystem.contacts.ui.contact_list

import com.example.emergencysystem.contacts.data.Contacts


sealed class ContactListEvent {
    data class OnDeleteTodoClick(val contact: Contacts): ContactListEvent()
    data class OnDoneChange(val contact: Contacts, val isDone: Boolean): ContactListEvent()
    object OnUndoDeleteClick: ContactListEvent()
    data class OnTodoClick(val contact: Contacts): ContactListEvent()
    object OnAddTodoClick: ContactListEvent()
}
