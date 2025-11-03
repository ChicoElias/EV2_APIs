package cl.duoc.ev2apis.ui.screen

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import cl.duoc.ev2apis.domain.model.SampleApis
import cl.duoc.ev2apis.R

@Composable
fun ApiDetailScreen(apiId: String, onShowMessage: (String) -> Unit) {
    val api = SampleApis.firstOrNull { it.id == apiId }
    var avatar by remember { mutableStateOf<String?>(null) }
    var attempted by remember { mutableStateOf(false) }

    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            attempted = true
            if (uri == null) {
                onShowMessage("Permiso/selección cancelada. Vuelve a intentarlo.")
            } else {
                avatar = uri.toString()
            }
        }
    )

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(api?.name ?: "API", style = MaterialTheme.typography.headlineSmall)
        Text(api?.description ?: "Descripción", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(8.dp))

        Row {
            Button(onClick = {
                onShowMessage("Suscripción simulada a ${api?.name}")
            }) { Text("Suscribirme") }

            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                picker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }) { Text("Elegir imagen (nativo)") }
        }

        Spacer(Modifier.height(16.dp))
        AsyncImage(
            model = avatar,
            contentDescription = "Imagen seleccionada por el usuario",
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RectangleShape)
        )
    }
}
