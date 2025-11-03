package cl.duoc.ev2apis.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.duoc.ev2apis.domain.model.ApiProduct
import cl.duoc.ev2apis.domain.model.SampleApis
import cl.duoc.ev2apis.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ApiListScreen(
    onOpenDetail: (String) -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenProfile: () -> Unit,
    vm: MainViewModel = viewModel()
) {
    var query by remember { mutableStateOf("") }
    val state by vm.state.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Buscar API") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            AssistChip(onClick = onOpenFavorites, label = { Text("Favoritos (${state.favorites.size})") })
            AssistChip(onClick = onOpenProfile, label = { Text("Perfil") })
        }

        Spacer(Modifier.height(8.dp))
        val list = SampleApis.filter { it.name.contains(query, ignoreCase = true) }
        LazyColumn {
            items(list) { api ->
                ApiRow(api = api, isFav = state.favorites.contains(api.id), onClick = { onOpenDetail(api.id) }) {
                    vm.toggleFavorite(api.id)
                }
                Divider()
            }
        }
    }
}

@Composable
private fun ApiRow(api: ApiProduct, isFav: Boolean, onClick: () -> Unit, onToggleFav: () -> Unit) {
    Row(
        Modifier.fillMaxWidth().clickable { onClick() }.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(api.name, style = MaterialTheme.typography.titleMedium)
            Text(api.description, style = MaterialTheme.typography.bodyMedium)
            Text("$${api.priceMonthly}/mes", style = MaterialTheme.typography.bodySmall)
        }
        IconButton(onClick = onToggleFav) {
            AnimatedVisibility(visible = isFav, enter = fadeIn(tween(200)), exit = fadeOut(tween(200))) {
                Icon(Icons.Default.Favorite, contentDescription = "Quitar de favoritos", tint = MaterialTheme.colorScheme.primary)
            }
            AnimatedVisibility(visible = !isFav, enter = fadeIn(tween(200)), exit = fadeOut(tween(200))) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Agregar a favoritos")
            }
        }
    }
}

// Icons
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
