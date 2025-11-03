package cl.duoc.ev2apis

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import cl.duoc.ev2apis.ui.theme.AppTheme
import cl.duoc.ev2apis.navigation.AppNav

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Runtime notification permission (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val launcher = registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { /* ignored: UI will fallback gracefully */ }
            launcher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }

        setContent {
            AppTheme {
                AppNav()
            }
        }
    }
}
