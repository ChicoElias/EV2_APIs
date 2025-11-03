package cl.duoc.ev2apis.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cl.duoc.ev2apis.domain.validation.FormValidator
import cl.duoc.ev2apis.ui.components.AnimatedPrimaryButton

@Composable
fun AuthScreen(
    onAuthOk: () -> Unit,
    onShowMessage: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var accepted by remember { mutableStateOf(false) }
    var minLen by remember { mutableStateOf(6) }

    val emailError = FormValidator.validateEmail(email)
    val passError = FormValidator.validatePassword(pass, minLen = minLen, requireUpper = true)
    val canSubmit = FormValidator.canSubmit(email, pass, accepted, minLen = minLen, requireUpper = true)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            isError = emailError != null,
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") },
            trailingIcon = {
                if (emailError != null) Icon(Icons.Filled.Error, contentDescription = "Error email", tint = MaterialTheme.colorScheme.error)
            },
            supportingText = { AnimatedVisibility(visible = emailError != null, enter = fadeIn(), exit = fadeOut()) {
                Text(emailError ?: "", color = MaterialTheme.colorScheme.error)
            }},
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Contraseña") },
            isError = passError != null,
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Contraseña") },
            trailingIcon = {
                if (passError != null) Icon(Icons.Filled.Error, contentDescription = "Error contraseña", tint = MaterialTheme.colorScheme.error)
            },
            supportingText = { AnimatedVisibility(visible = passError != null, enter = fadeIn(), exit = fadeOut()) {
                Text(passError ?: "", color = MaterialTheme.colorScheme.error)
            }},
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = accepted, onCheckedChange = { accepted = it })
            Text("Acepto Términos y Condiciones", modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(Modifier.height(12.dp))

        AnimatedPrimaryButton(
            text = "Ingresar",
            enabled = canSubmit,
            onClick = {
                onShowMessage("Bienvenido 👋")
                onAuthOk()
            }
        )

        Spacer(Modifier.height(12.dp))

        // Botón preparado para la defensa (endurecer regla en vivo)
        AssistChip(
            onClick = { minLen = if (minLen == 6) 8 else 6 },
            label = { Text("Cambiar regla: minLen=$minLen") }
        )
    }
}
