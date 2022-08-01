package com.example.emergencysystem.contacts.ui.add_edit_contact

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emergencysystem.contacts.data.ContactRepository
import com.example.emergencysystem.contacts.data.Contacts
import com.example.emergencysystem.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditContactViewModel @Inject constructor(
    private val repository: ContactRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var contact by mutableStateOf<Contacts?>(null)
        private set

    var name by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
        private set

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val contactId = savedStateHandle.get<Int>("contactId")!!
        if(contactId != -1) {
            viewModelScope.launch {
                repository.getContactById(contactId)?.let { contacts ->
                    name = contacts.name
                    phone = contacts.phone ?: ""
                    this@AddEditContactViewModel.contact = contacts
                }
            }
        }
    }

    fun onEvent(event: AddEditContactEvent) {
        when(event) {
            is AddEditContactEvent.OnTitleChange -> {
                name = event.title
            }
            is AddEditContactEvent.OnDescriptionChange -> {
                phone = event.description
            }
            is AddEditContactEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if(name.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "The title can't be empty"
                        ))
                        return@launch
                    }
                    repository.insert(
                        Contacts(
                            name = name,
                            phone = phone,
                            isDone = contact?.isDone ?: false,
                            id = contact?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
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
