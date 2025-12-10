package uce.edu.ec.Model.Entity

data class Inventario(
    val id: Int,
    // Mapa que asocia el nombre del libro (o ID) con la cantidad disponible
    val librosStock: MutableMap<String, Int>
)