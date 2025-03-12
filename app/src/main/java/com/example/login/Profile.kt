package com.example.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Profile() {
    Column (modifier = Modifier
        .background(Color(0XFFF7EAEB))
        .fillMaxSize()) {
        // Border box
        Box(
            modifier = Modifier
                .height(244.dp)
                .width(412.dp)
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color(0XFFC41532),
                            Color(0XFF431B3B)
                        )
                    )
                ),
            Alignment.TopCenter
        ) {
            Text("Profil", modifier = Modifier.padding(55.dp), fontSize = 20.sp, color = Color.White)

            Image(painter = painterResource(R.drawable.gear_settings),
                contentDescription = "Gear Settings Icon", modifier = Modifier.size(24.dp))
        }

    }
}

@Preview
@Composable
fun ProfileReview() {
    Profile()
}
