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
                // Estado simple para controlar la navegación: "login", "register", "bookList"
                var currentScreen by remember { mutableStateOf("login") }
                // Estado para almacenar el nombre del usuario logueado (opcional, para bienvenida)
                var loggedUser by remember { mutableStateOf("") }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (currentScreen) {
                        "login" -> {
                            LoginUser(
                                modifier = Modifier.padding(innerPadding),
                                onLoginClick = { username, _ ->
                                    loggedUser = username
                                    Toast.makeText(this, "Bienvenido: $username", Toast.LENGTH_SHORT).show()
                                    currentScreen = "bookList" // Navegar a la lista de libros
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
                                onLogoutClick = {
                                    currentScreen = "login"
                                    loggedUser = ""
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
