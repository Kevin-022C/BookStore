package uce.edu.ec.View.Componens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    onLogoutClick: () -> Unit = {}
) {
    val serviceLibros = remember { ServiceLibros() }
    // Pasamos el servicio de inventario a los items
    val serviceInventario = remember { ServiceInventario() }
    
    var libros by remember { mutableStateOf<List<Libros>>(emptyList()) }

    LaunchedEffect(Unit) {
        libros = serviceLibros.obtenerTodos()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
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
                // Obtenemos el stock específico para este libro
                val stock = serviceInventario.obtenerStock(libro.titulo)
                BookItem(libro = libro, stock = stock)
            }
        }
    }
}

@Composable
fun BookItem(libro: Libros, stock: Int) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
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
                text = "Año de publicacion: ${libro.fechaPublicacion}",
                style = MaterialTheme.typography.bodyMedium
            )
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
                
                // Mostrar disponibilidad
                if (stock > 0) {
                    Text(
                        text = "Disponible: $stock",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF4CAF50) // Verde
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
