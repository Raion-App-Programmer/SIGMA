import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R

@Composable
fun Profile() {
    Box( // Use Box for overlapping effect
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFFF7EAEB))
    ) {
        // Head NavBar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(244.dp)
                .clip(RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color(0XFFC41532),
                            Color(0XFF431B3B)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Profil",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.offset(y = (-50).dp)
            )
            Image(
                painter = painterResource(id = R.drawable.gear_settings),
                contentDescription = "Gear Settings",
                modifier = Modifier
                    .size(24.dp)
                    .offset(y = (-50).dp, x = 140.dp)
            )
        }

        // White box (Overlapping)
        Column( // Use Column for vertical layout within the Box
            modifier = Modifier
                .align(Alignment.TopCenter) // Align to top center
                .padding(top = 150.dp) // Adjust top padding to overlap
        ) {
            Box(
                modifier = Modifier
                    .width(371.dp)
                    .height(149.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.White)
            ) {
                // Profile image and text inside the white box
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Diandra Salim",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "diandrasalim@gmail.com",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Ubah Profil",
                        fontSize = 16.sp,
                        color = Color(0XFFC41532),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            // Profile picture container
            Box (
                modifier = Modifier
                    .offset(y = (-200).dp, x = 140.dp)
                    .border(5.dp, color = Color.White, RoundedCornerShape(50.dp)),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_picture_image),
                    contentDescription = "Profile Picture",
                    Modifier.size(100.dp)
                )
            }

            Box(
                modifier = Modifier
                    .offset(y = (-80).dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .width(371.dp)
                    .height(788.dp)

            ) {
                Text("Lacak Laporanmu!", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.TopCenter))
            }
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    Profile()
}