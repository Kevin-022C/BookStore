package uce.edu.ec.Model.Service

import uce.edu.ec.Model.Data.InventarioData
import uce.edu.ec.Model.Entity.Inventario
import uce.edu.ec.Model.Repository.IreInventario

class ServiceInventario : IreInventario {
    override fun save(inventario: Inventario): Inventario {
        TODO("Not yet implemented")
    }

    // Obtiene la cantidad disponible dado el título del libro
    override fun obtenerStock(tituloLibro: String): Int {
        // Buscamos en el primer inventario (asumiendo que hay uno principal)
        val inventario = InventarioData.inventarios.firstOrNull()
        return inventario?.librosStock?.get(tituloLibro) ?: 0
    }

    // actualizar stock si lo necesitas luego
    override fun actualizarStock(tituloLibro: String, cantidad: Int) {
        val inventario = InventarioData.inventarios.firstOrNull()
        inventario?.librosStock?.put(tituloLibro, cantidad)
    }
}
