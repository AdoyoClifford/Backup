package com.example.emergencysystem.contacts.ui.contact_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emergencysystem.contacts.data.Contacts

@Composable
fun TodoItem(
    contact: Contacts,
    onEvent: (ContactListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = contact.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    onEvent(ContactListEvent.OnDeleteTodoClick(contact))
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
            contact.phone?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it)
            }
        }
        Checkbox(
            checked = contact.isDone,
            onCheckedChange = { isChecked ->
                onEvent(ContactListEvent.OnDoneChange(contact, isChecked))
            }
        )
    }
}