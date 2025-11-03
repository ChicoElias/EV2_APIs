package cl.duoc.ev2apis.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.duoc.ev2apis.ui.screen.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNav() {
    val nav = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { SmallTopAppBar(title = { Text("EV2 APIs") }) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { inner ->
        NavHost(navController = nav, startDestination = "auth", modifier = androidx.compose.ui.Modifier.padding(inner)) {
            composable("auth") {
                AuthScreen(
                    onAuthOk = { nav.navigate("catalog") },
                    onShowMessage = { msg ->
                        LaunchedEffect(msg) { snackbarHostState.showSnackbar(msg) }
                    }
                )
            }
            composable("catalog") {
                ApiListScreen(
                    onOpenDetail = { id -> nav.navigate("detail/$id") },
                    onOpenFavorites = { nav.navigate("favorites") },
                    onOpenProfile = { nav.navigate("profile") }
                )
            }
            composable("detail/{id}") {
                val id = it.arguments?.getString("id") ?: "0"
                ApiDetailScreen(apiId = id) { msg ->
                    LaunchedEffect(msg) { snackbarHostState.showSnackbar(msg) }
                }
            }
            composable("favorites") { FavoritesScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}
