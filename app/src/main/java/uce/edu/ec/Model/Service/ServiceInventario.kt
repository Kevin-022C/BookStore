package uce.edu.ec.Model.Service

import uce.edu.ec.Model.Data.InventarioData

class ServiceInventario {

    // Obtiene la cantidad disponible dado el título del libro
    fun obtenerStock(tituloLibro: String): Int {
        // Buscamos en el primer inventario (asumiendo que hay uno principal)
        val inventario = InventarioData.inventarios.firstOrNull()
        return inventario?.librosStock?.get(tituloLibro) ?: 0
    }
    
    // actualizar stock si lo necesitas luego
    fun actualizarStock(tituloLibro: String, cantidad: Int) {
        val inventario = InventarioData.inventarios.firstOrNull()
        inventario?.librosStock?.put(tituloLibro, cantidad)
    }
}
