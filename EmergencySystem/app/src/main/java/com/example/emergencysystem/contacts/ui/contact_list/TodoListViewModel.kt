package com.example.emergencysystem.contacts.ui.contact_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emergencysystem.contacts.data.ContactRepository
import com.example.emergencysystem.contacts.data.Contacts
import com.example.emergencysystem.util.Screens
import com.example.emergencysystem.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: ContactRepository, savedStateHandle: SavedStateHandle
): ViewModel() {

    val todos = repository.getAll()

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedContact: Contacts? = null

    init {
        val id = savedStateHandle.get<Int>("id")!!
    }

    fun onEvent(event: ContactListEvent) {
        when(event) {
            is ContactListEvent.OnTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Screens.ADD_EDIT_TODO.route + "?todoId=${event.contact.id}"))
            }
            is ContactListEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Screens.ADD_EDIT_TODO.route))
            }
            is ContactListEvent.OnUndoDeleteClick -> {
                deletedContact?.let { contacts ->
                    viewModelScope.launch {
                        repository.insert(contacts)
                    }
                }
            }
            is ContactListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    deletedContact = event.contact
                    repository.delete(event.contact)
                    sendUiEvent(UiEvent.ShowSnackbar(
                        message = "Todo deleted",
                        action = "Undo"
                    ))
                }
            }
            is ContactListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insert(
                        event.contact.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}