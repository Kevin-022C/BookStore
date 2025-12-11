package uce.edu.ec.View.Componens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp


import uce.edu.ec.Model.Data.UsuarioData
import uce.edu.ec.Model.Entity.Usuario
import uce.edu.ec.Model.Service.ServiceUsuario

@Composable
fun RegisterUser(
    modifier: Modifier = Modifier,
    onRegisterSuccess: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    var nombreUsuario by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var metodoPago by remember { mutableStateOf("") }
    
    val serviceUsuario = remember { ServiceUsuario() }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // Permite scroll si el teclado tapa campos
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Crear Cuenta",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = nombreUsuario, 
            onValueChange = { nombreUsuario = it }, 
            label = { Text("Usuario *") }, 
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = password, 
            onValueChange = { password = it }, 
            label = { Text("Contraseña *") }, 
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedTextField(
            value = nombre, 
            onValueChange = { nombre = it }, 
            label = { Text("Nombre") }, 
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = apellido, 
            onValueChange = { apellido = it }, 
            label = { Text("Apellido") }, 
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = correo, 
            onValueChange = { correo = it }, 
            label = { Text("Correo") }, 
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = metodoPago, 
            onValueChange = { metodoPago = it }, 
            label = { Text("Método de Pago Favorito") }, 
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (nombreUsuario.isNotEmpty() && password.isNotEmpty()) {
                    // Generación simple de ID: maxId + 1
                    val newId = (UsuarioData.usuarios.maxOfOrNull { it.id } ?: 0) + 1
                    
                    val newUser = Usuario(
                        id = newId,
                        nombreUsuario = nombreUsuario,
                        nombre = nombre,
                        apellido = apellido,
                        correo = correo,
                        password = password,
                        rol = "CLIENTE",
                        metodoPagoFavorito = metodoPago
                    )
                    
                    // Verificar si el usuario ya existe
                    if (serviceUsuario.obtenerUsuarioPorNombreUsuario(nombreUsuario) == null) {
                        serviceUsuario.save(newUser)
                        Toast.makeText(context, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                        onRegisterSuccess()
                    } else {
                        Toast.makeText(context, "El nombre de usuario ya existe", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Usuario y Contraseña son obligatorios", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        TextButton(onClick = onBackClick) {
            Text("¿Ya tienes cuenta? Iniciar Sesión")
        }
    }
}
