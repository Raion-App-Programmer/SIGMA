package com.example.login

import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember

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
                    NewsCard(newsItem, navController)

                }
            }
        }
    }
}

@Composable
fun NewsCard(newsItem: NewsItem, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)
        ){
            Text(text = newsItem.title, style = MaterialTheme.typography.titleMedium)
            Text(text = newsItem.date, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview
@Composable
fun BeritaTerkiniPreview() {
    BeritaTerkini(
        navController = rememberNavController(),
        viewModel = viewModel()
    )
}
