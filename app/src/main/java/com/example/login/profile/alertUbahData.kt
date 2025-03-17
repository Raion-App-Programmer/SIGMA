import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.alertDataDiubah

@Composable
fun alertUbahData(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    var showSuccessDialog by remember { mutableStateOf(false) }

    if (!showSuccessDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "❓",
                        fontSize = 32.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Apakah Anda yakin ingin mengubah data diri Anda?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                Text(
                    text = "Perubahan ini akan menggantikan informasi sebelumnya.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            },
            dismissButton = {
            },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Batalkan", color = Color.Red)
                    }
                    Button(
                        onClick = {
                            showSuccessDialog = true
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Ya, Ganti Data", color = Color.White)
                    }
                }
            },

            modifier = Modifier.height(306.dp)
        )
    }

    // Menampilkan alert sukses setelah konfirmasi
    if (showSuccessDialog) {
        alertDataDiubah {
            showSuccessDialog = false
            onConfirm() // Menjalankan aksi konfirmasi setelah alert sukses tertutup
        }
    }
}
