package cl.duoc.ev2apis.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.duoc.ev2apis.viewmodel.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProfileScreen(vm: MainViewModel = viewModel()) {
    val state by vm.state.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Perfil", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Tema oscuro")
            Switch(checked = state.dark, onCheckedChange = { vm.setDark(it) })
        }
        Divider()
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Notificaciones")
            Switch(checked = state.notify, onCheckedChange = { vm.setNotify(it) })
        }
        Divider()
        Spacer(Modifier.height(8.dp))
        Button(onClick = { vm.setToken(null) }) { Text("Cerrar sesión") }
    }
}
