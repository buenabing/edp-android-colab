package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(Color(0xFF121212)), // Screen background
                        contentAlignment = Alignment.Center
                    ) {
                        BusinessCard()
                    }
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    // Colors inspired by the screenshot
    val backgroundColor = Color(0xFF222A2E) // Dark Charcoal/Navy
    val accentColor = Color(0xFFC5A17A)     // Muted Gold/Tan

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(accentColor), // Gold background for the screen
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Step 3: Circular avatar
                ProfilePicture(accentColor)

                Spacer(modifier = Modifier.height(16.dp))

                // Step 4: Name and Title
                Text(
                    text = "BUEN MARIE B. ABING",
                    color = accentColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "IT STUDENT",
                    color = accentColor.copy(alpha = 0.7f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Step 5: Contact Rows
                ContactRow(
                    icon = Icons.Default.Phone,
                    text = "09276684249",
                    color = accentColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                ContactRow(
                    icon = Icons.Default.Email,
                    text = "bmabing62681@liceo.edu.ph",
                    color = accentColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                ContactRow(
                    icon = Icons.Default.LocationOn,
                    text = "Zone 6, Bugo, CDO",
                    color = accentColor
                )
            }
        }
    }
}

@Composable
fun ContactRow(icon: ImageVector, text: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            color = color,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun ProfilePicture(borderColor: Color) {
    // Step 3: Size -> Clip -> Border order is critical
    Image(
        painter = painterResource(id = R.drawable.profile_pic),
        contentDescription = "Profile Picture",
        modifier = Modifier
            .size(120.dp)
            .clip(androidx.compose.foundation.shape.CircleShape)
            .border(4.dp, borderColor, androidx.compose.foundation.shape.CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    MyApplicationTheme {
        Box(modifier = Modifier.fillMaxSize().background(Color.Gray), contentAlignment = Alignment.Center) {
            BusinessCard()
        }
    }
}