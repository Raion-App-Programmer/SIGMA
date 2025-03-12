import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.test.services.storage.file.PropertyFile
import coil3.compose.AsyncImage
import com.example.login.NewsViewModel
import java.lang.reflect.Modifier
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BeritaDetail(newsId: String,  viewModel: NewsViewModel = viewModel()) {
    val newsItem by viewModel.newsItem.observeAsState()

    LaunchedEffect(newsId) {
        viewModel.getNewsById(newsId)
    }

    if (newsItem != null) {
        Column {
            Text(text = newsItem!!.title, style = MaterialTheme.typography.headlineMedium)
            Text(text = newsItem!!.description)
        }
    } else {
        Text(text = "News not found", style = MaterialTheme.typography.headlineSmall)
    }
}

