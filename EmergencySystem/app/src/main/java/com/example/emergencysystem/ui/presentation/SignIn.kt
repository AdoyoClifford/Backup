package com.example.emergencysystem.ui.presentation

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.emergencysystem.util.Screens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LogInScreen(auth: FirebaseAuth, navController: NavController) {
    val focusManager = LocalFocusManager.current
    var email by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }

    val isEmailValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    val isPasswordValid by derivedStateOf {
        password.length > 6
    }

    val isPasswordVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome back",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(
                1.dp,
                Color.Black
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email Address") },
                    placeholder = { Text("abc@domain.com") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                    ), keyboardActions = KeyboardActions(
                        onNext = {focusManager.moveFocus(FocusDirection.Down)}
                    ),
                    isError = isEmailValid
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password") },
                    placeholder = { Text("abc@domain.com") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    ), keyboardActions = KeyboardActions(
                        onNext = {focusManager.clearFocus()}
                    ),
                    isError = isPasswordValid
                )

                Button(
                    onClick = {auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener {
                            if(it.isSuccessful) {
                                navController.navigate(Screens.HomeScreen.route)
                            } else {
                                Log.d(MainActivity.TAG,"Failed",it.exception)
                            }
                        }},
                    enabled = isEmailValid && isPasswordValid,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text(text = "Log In")

                }
            }

        }

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = "Forgot password",
                    color = Color.Blue,
                    modifier = Modifier.padding(end = 8.dp)
                )

            }
        }

        Button(
            onClick = {  },
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(text = "Register", color = Color.Black)


        }

    }

}