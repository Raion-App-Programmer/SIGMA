package com.example.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Lapor() {
    var text by remember { mutableStateOf("") } // Move `text` outside Row

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFFF7EAEB))
    ) {
        Column {
            // Top Bar
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                    .fillMaxWidth()
                    .height(119.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0XFFC41532),
                                Color(0XFF431B3B)
                            )
                        )
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = "White arrow",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(34.dp))
                    Text(
                        "Lapor Sigma",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.weight(1f)) // Flexible spacing
                    Text(
                        "1/3",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }

            // Nama Pelapor
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Nama Pelapor",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 20.dp)
            )
            Spacer(modifier = Modifier.height(3.dp))
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Nama")},
                modifier = Modifier
                    .width(371.dp)
                    .height(56.dp)
                    .padding(start = 20.dp)
            )
        }
    }
}

@Preview
@Composable
fun LaporPreview() {
    Lapor()
}
