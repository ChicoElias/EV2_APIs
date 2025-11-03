package cl.duoc.ev2apis.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.duoc.ev2apis.domain.model.SampleApis
import cl.duoc.ev2apis.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FavoritesScreen(vm: MainViewModel = viewModel()) {
    val state by vm.state.collectAsState()
    val list = SampleApis.filter { state.favorites.contains(it.id) }
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Favoritos", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(list) { api ->
                Text(api.name, style = MaterialTheme.typography.titleMedium)
                Text(api.description)
                Divider()
            }
        }
    }
}
