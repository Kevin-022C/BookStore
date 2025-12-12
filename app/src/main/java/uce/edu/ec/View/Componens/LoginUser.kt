package uce.edu.ec.View.Componens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uce.edu.ec.Model.Service.ServiceUsuario

@Composable
fun LoginUser(
    modifier: Modifier = Modifier,
    onLoginClick: (String, String) -> Unit = { _, _ -> },
    onRegisterClick: () -> Unit = {}
) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    
    // Instancia del servicio para validar (se mantiene entre recomposiciones)
    val serviceUsuario = remember { ServiceUsuario() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bienvenido",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { 
                nombre = it
                isError = false // Limpiar error al escribir
            },
            label = { Text("Nombre") },
            singleLine = true,
            isError = isError,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = apellido,
            onValueChange = { 
                apellido = it
                isError = false // Limpiar error al escribir
            },
            label = { Text("Apellido") },
            // Quitamos la PasswordVisualTransformation para ver el texto
            singleLine = true,
            isError = isError,
            modifier = Modifier.fillMaxWidth()
        )

        if (isError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Nombre o Apellido incorrectos",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { 
                // Lógica de validación por Nombre y Apellido
                val usuarioEncontrado = serviceUsuario.obtenerUsuarioPorNombreYApellido(nombre, apellido)
                
                if (usuarioEncontrado != null) {
                    onLoginClick(nombre, apellido)
                } else {
                    isError = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        TextButton(onClick = onRegisterClick) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}
