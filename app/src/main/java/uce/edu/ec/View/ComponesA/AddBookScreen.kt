package uce.edu.ec.View.ComponesA

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import uce.edu.ec.Model.Data.LibrosData
import uce.edu.ec.Model.Entity.Libros
import uce.edu.ec.Model.Service.ServiceLibros

@Composable
fun AddBookScreen(
    modifier: Modifier = Modifier,
    onBookAdded: () -> Unit = {},
    onCancelClick: () -> Unit = {}
) {
    var titulo by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var anioPublicasion by remember { mutableStateOf("") }
    var precioStr by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }

    val serviceLibros = remember { ServiceLibros() }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Agregar Nuevo Libro",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título *") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = autor,
            onValueChange = { autor = it },
            label = { Text("Autor *") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = anioPublicasion,
            onValueChange = { anioPublicasion = it },
            label = { Text("Año-Publicasión *") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = precioStr,
            onValueChange = { precioStr = it },
            label = { Text("Precio *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = imagen,
            onValueChange = { imagen = it },
            label = { Text("Nombre de Imagen (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = onCancelClick,
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Button(
                onClick = {
                    if (titulo.isNotEmpty() && autor.isNotEmpty() && precioStr.isNotEmpty()) {
                        try {
                            val precio = precioStr.toDouble()
                            // Generar ID simple
                            val newId = (LibrosData.libros.maxOfOrNull { it.id } ?: 0) + 1
                            
                            val nuevoLibro = Libros(
                                id = newId,
                                titulo = titulo,
                                autor = autor,
                                precio = precio,
                                descripcion = descripcion,
                                fechaPublicacion =anioPublicasion,
                                imagen ="null",

                            )
                            
                            serviceLibros.save(nuevoLibro)
                            Toast.makeText(context, "Libro agregado correctamente", Toast.LENGTH_SHORT).show()
                            onBookAdded()
                            
                        } catch (e: NumberFormatException) {
                            Toast.makeText(context, "El precio debe ser un número válido", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "Por favor complete los campos obligatorios", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Guardar")
            }
        }
    }
}
