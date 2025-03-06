import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo

@Composable
fun PanggilSigma() {
    // Background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFFf7eaeb))
    ) {
        // container for emergency text
        Column (
          modifier = Modifier
              .fillMaxWidth()
              .height(135.dp)
              .align(Alignment.TopCenter)
              .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
              .background(Color.White)
        ) {
            // box start for text
            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center) {
                Text(
                    "Hanya Untuk Darurat",
                    color = Color(0XFFC41532),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            // box end
        }

        Spacer(Modifier.height(80.dp))


    }
}

@Preview
@Composable
fun PanggilSigmaPreview() {
    PanggilSigma()
}