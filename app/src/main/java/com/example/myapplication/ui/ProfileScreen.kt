package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.domain.model.User
import com.example.myapplication.ui.theme.AppGradient

@Composable
fun ProfileScreen(user: User, onLogout: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(Color.White.copy(alpha = 0.8f), CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(Color.White.copy(alpha = 0.8f), CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(Color.White.copy(alpha = 0.8f), CircleShape)
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "You have successfully logged in!",
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2C0508)
                )
                Text(
                    text = "Welcome back, ${user.fullName}",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7A0D15)
                )
            }

            ProfileRow("Full Name", user.fullName)
            ProfileRow("Email", user.email)
            ProfileRow("Birthdate", user.birthdate)
            ProfileRow("User ID", user.id)

            ageFrom(user.birthdate)?.let {
                ProfileRow("Age", "$it")
            }

            Button(
                onClick = onLogout,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF2C0508)
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = "Log out",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileRow(label: String, value: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2C0508)
        )
        Surface(
            shape = RoundedCornerShape(2.dp),
            color = Color(0xFF800015),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }
    }
}

fun ageFrom(birthdate: String): Int? {
    val parts = birthdate.split("-")
    if (parts.size != 3) return null
    val y = parts[0].toIntOrNull() ?: return null
    val m = parts[1].toIntOrNull() ?: return null
    val d = parts[2].toIntOrNull() ?: return null

    val now = java.util.Calendar.getInstance()
    val ty = now.get(java.util.Calendar.YEAR)
    val tm = now.get(java.util.Calendar.MONTH) + 1
    val td = now.get(java.util.Calendar.DAY_OF_MONTH)

    var age = ty - y
    if (tm < m || (tm == m && td < d)) {
        age -= 1
    }
    return age
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        user = User(
            id = "55",
            fullName = "Buen Marie B. Abing",
            email = "bmabing62681@liceo.edu.ph",
            birthdate = "2006-07-12"
        ),
        onLogout = {}
    )
}
