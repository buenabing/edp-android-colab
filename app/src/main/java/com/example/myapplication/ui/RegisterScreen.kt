package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.AppGradient

@Composable
fun RegisterScreen(
    state: AuthUiState,
    onCreate: (String, String, String, String) -> Unit,
    onGoToLogin: () -> Unit
) {
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var birthdate by rememberSaveable { mutableStateOf("") }

    val isLoading = state is AuthUiState.Loading

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color(0xFF800015),
        unfocusedContainerColor = Color(0xFF800015),
        disabledContainerColor = Color(0xFF800015),
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        focusedLabelColor = Color(0xFF2C0508),
        unfocusedLabelColor = Color(0xFF2C0508),
        focusedBorderColor = Color(0xFF500008),
        unfocusedBorderColor = Color(0xFF7A0012),
        focusedPlaceholderColor = Color.White.copy(alpha = 0.6f),
        unfocusedPlaceholderColor = Color.White.copy(alpha = 0.6f),
        cursorColor = Color.White
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Create account",
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2C0508),
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full name", fontWeight = FontWeight.Bold) },
                singleLine = true,
                colors = textFieldColors,
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", fontWeight = FontWeight.Bold) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = textFieldColors,
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", fontWeight = FontWeight.Bold) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                colors = textFieldColors,
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = birthdate,
                onValueChange = { birthdate = it },
                label = { Text("Birthdate", fontWeight = FontWeight.Bold) },
                placeholder = { Text("YYYY-MM-DD") },
                singleLine = true,
                colors = textFieldColors,
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillMaxWidth()
            )

            if (state is AuthUiState.Error) {
                Text(
                    text = state.message,
                    color = Color(0xFFFF8A80),
                    fontWeight = FontWeight.Bold
                )
            }
            if (state is AuthUiState.AccountCreated) {
                Text(
                    text = "Account created for ${state.name}. You can now log in.",
                    color = Color(0xFFA5D6A7),
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = { onCreate(fullName, email, password, birthdate) },
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF2C0508)
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = Color(0xFF2C0508)
                    )
                } else {
                    Text(
                        text = "Create account",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            TextButton(onClick = onGoToLogin) {
                Text(
                    text = "Already have an account? Log in",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        state = AuthUiState.Idle,
        onCreate = { _, _, _, _ -> },
        onGoToLogin = {}
    )
}
