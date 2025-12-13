package uce.edu.ec.View.Componens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uce.edu.ec.Model.Entity.Libros
import uce.edu.ec.Model.Service.ServiceInventario
import uce.edu.ec.Model.Service.ServiceLibros

@Composable
fun BookListScreen(
    modifier: Modifier = Modifier,
    userRole: String, // Recibimos el rol del usuario
    onLogoutClick: () -> Unit = {},
    onAddBookClick: () -> Unit = {} // Nuevo callback para ir a agregar libro
) {
    val serviceLibros = remember { ServiceLibros() }
    val serviceInventario = remember { ServiceInventario() }
    
    // Usamos un estado que fuerce la recomposición al eliminar
    var libros by remember { mutableStateOf<List<Libros>>(emptyList()) }
    // Estado para disparar la actualización
    var refreshTrigger by remember { mutableStateOf(0) }

    // Función para recargar la lista
    fun refreshLibros() {
        libros = serviceLibros.obtenerTodos()
    }

    LaunchedEffect(refreshTrigger) {
        refreshLibros()
    }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            // Solo mostramos el botón si es ADMIN
            if (userRole == "ADMIN") {
                FloatingActionButton(onClick = onAddBookClick) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar Libro")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text(
                    text = "Catálogo de Libros",
                    style = MaterialTheme.typography.headlineMedium
                )
                TextButton(onClick = onLogoutClick) {
                    Text("Cerrar Sesión")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(libros) { libro ->
                    val stock = serviceInventario.obtenerStock(libro.titulo)
                    BookItem(
                        libro = libro, 
                        stock = stock,
                        isAdmin = userRole == "ADMIN",
                        onDeleteClick = {
                            serviceLibros.delete(libro)
                            refreshTrigger++ // Forzar recarga de la lista
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BookItem(
    libro: Libros, 
    stock: Int, 
    isAdmin: Boolean = false,
    onDeleteClick: () -> Unit = {}
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = libro.titulo,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Autor: ${libro.autor}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Fecha de publicación: ${libro.fechaPublicacion}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                
                // Botón de eliminar visible solo para ADMIN
                if (isAdmin) {
                    IconButton(onClick = onDeleteClick) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar libro",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = libro.descripcion,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$${libro.precio}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                
                if (stock > 0) {
                    Text(
                        text = "Disponible: $stock",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF4CAF50)
                    )
                } else {
                    Text(
                        text = "Agotado",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
