package com.example.emergencysystem.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.emergencysystem.contacts.ui.add_edit_contact.AddEditContactScreen
import com.example.emergencysystem.contacts.ui.contact_list.ContactListScreen
import com.example.emergencysystem.ui.theme.EmergencySystemTheme
import com.example.emergencysystem.util.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }


    override fun onCreate(savedInstanceState: Bundle?,) {
        super.onCreate(savedInstanceState)
        setContent {
            EmergencySystemTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color( 0xfff2f2f2)
                ) {
                   Navigation()
                    //MapScreen()
                   // ContactListScreen(onNavigate = {})
                   AddEditContactScreen(onPopBackStack = { /*TODO*/ })
            }
        }
    }
}}

