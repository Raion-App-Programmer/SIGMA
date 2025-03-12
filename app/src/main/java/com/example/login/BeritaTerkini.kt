package com.example.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun BeritaTerkini(navController: NavController, viewModel: NewsViewModel = viewModel()) {
    val newsList by viewModel.newsList.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                    .width(412.dp)
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
                Text(
                    "Berita Hari Ini",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn {
                items(newsList) { newsItem ->
                    NewsCard(
                        imageUrl = newsItem.imageUrl,
                        date = newsItem.date,
                        title = newsItem.title,
                        author = newsItem.author,
                        onClick = { }
                    )

                }
            }
        }
    }
}

@Composable
fun NewsCard(
    imageUrl: String,
    date: String,
    title: String,
    author: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() } // Makes it clickable
    ) {
        // Background Image
        AsyncImage(
            model = imageUrl,
            contentDescription = "News Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Semi-transparent Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000)) // Semi-transparent black overlay
        )

        // Text & Button Overlay
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Date Label
            Text(
                text = date,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .background(Color(0x88FFFFFF), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )

            // Title & Author
            Column {
                Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = author, color = Color.White, fontSize = 12.sp)
            }

            // Read More Button
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(50),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Selengkapnya", color = Color.White)
            }
        }
    }
}


// Fake Preview Data (Avoid using viewModel in Previews)
@Preview
@Composable
fun BeritaTerkiniPreview() {
    val fakeNewsList = listOf(
        NewsItem("1", "Breaking News: Market Crash!", "March 12, 2025", "Some description"),
        NewsItem("2", "Weather Alert: Heavy Rain Expected", "March 11, 2025", "Another description")
    )
    val fakeViewModel = object : NewsViewModel() {
        @SuppressLint("UnrememberedMutableState")
        override val newsList: StateFlow<List<NewsItem>> = MutableStateFlow(fakeNewsList)
    }

    BeritaTerkini(navController = rememberNavController(), viewModel = fakeViewModel)
}