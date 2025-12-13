package uce.edu.ec

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import uce.edu.ec.Model.Service.ServiceUsuario
import uce.edu.ec.View.ComponesA.AddBookScreen
import uce.edu.ec.View.Componens.BookListScreen
import uce.edu.ec.View.Componens.LoginUser
import uce.edu.ec.View.Componens.RegisterUser
import uce.edu.ec.ui.theme.BookStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookStoreTheme {
                // Estado simple para controlar la navegación: "login", "register", "bookList", "addBook"
                var currentScreen by remember { mutableStateOf("login") }
                
                // Estado para almacenar datos del usuario logueado
                var loggedUserRole by remember { mutableStateOf("") }
                var loggedUserName by remember { mutableStateOf("") }
                
                val serviceUsuario = remember { ServiceUsuario() }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (currentScreen) {
                        "login" -> {
                            LoginUser(
                                modifier = Modifier.padding(innerPadding),
                                onLoginClick = { nombre, apellido ->
                                    // Buscar usuario para obtener su rol
                                    val usuario = serviceUsuario.obtenerUsuarioPorNombreYApellido(nombre, apellido)
                                    if (usuario != null) {
                                        loggedUserRole = usuario.rol
                                        loggedUserName = usuario.nombreUsuario
                                        Toast.makeText(this, "Bienvenido: ${usuario.nombre} (${usuario.rol})", Toast.LENGTH_SHORT).show()
                                        currentScreen = "bookList"
                                    }
                                },
                                onRegisterClick = {
                                    currentScreen = "register"
                                }
                            )
                        }
                        "register" -> {
                            RegisterUser(
                                modifier = Modifier.padding(innerPadding),
                                onRegisterSuccess = {
                                    currentScreen = "login"
                                },
                                onBackClick = {
                                    currentScreen = "login"
                                }
                            )
                        }
                        "bookList" -> {
                            BookListScreen(
                                modifier = Modifier.padding(innerPadding),
                                userRole = loggedUserRole, // Pasamos el rol
                                onLogoutClick = {
                                    currentScreen = "login"
                                    loggedUserRole = ""
                                    loggedUserName = ""
                                },
                                onAddBookClick = {
                                    currentScreen = "addBook"
                                }
                            )
                        }
                        "addBook" -> {
                            AddBookScreen(
                                modifier = Modifier.padding(innerPadding),
                                onBookAdded = {
                                    currentScreen = "bookList" // Volver a la lista tras guardar
                                },
                                onCancelClick = {
                                    currentScreen = "bookList"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
