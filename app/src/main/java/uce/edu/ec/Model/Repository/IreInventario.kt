package uce.edu.ec.Model.Repository

import uce.edu.ec.Model.Entity.Inventario

interface IreInventario {
    fun save(inventario: Inventario): Inventario
    fun actualizarStock(tituloLibro: String, cantidad: Int)
    fun obtenerStock(tituloLibro: String): Int

}