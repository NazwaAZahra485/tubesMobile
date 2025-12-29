package com.nazwakhayla.sekaiprofileviewer.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nazwakhayla.sekaiprofileviewer.viewModel.AuthViewModel

// TODO: Add Dependency buat Visibility

@Composable
fun LoginScreen(modifier: Modifier = Modifier, authViewModel: AuthViewModel = viewModel()) {
    var emailValue by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        TextField(
            value = emailValue,
            onValueChange = { emailValue = it },
            singleLine = true,
            label = { Text(text = "Email") },
            placeholder = { Text(text = "user@email.com") },
            leadingIcon = {
                Image(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person Icon"
                )
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(5.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Enter your password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Image(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Key Icon"
                )
            },
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(imageVector = image, "")
                }
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(5.dp))
        if (authViewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    authViewModel.signInEmailPassword(emailValue, password)
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Submit",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            authViewModel.errMsg?.let { msg ->
                Text(text = msg)
            }
        }
    }
}