package uce.edu.ec

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import uce.edu.ec.View.Componens.LoginUser
import uce.edu.ec.ui.theme.BookStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookStoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginUser(
                        modifier = Modifier.padding(innerPadding),
                        onLoginClick = { username, password ->
                            // Aquí puedes manejar la lógica de login
                            Toast.makeText(this, "Login: $username", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
