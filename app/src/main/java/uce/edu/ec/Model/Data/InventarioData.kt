package uce.edu.ec.Model.Data

import uce.edu.ec.Model.Entity.Inventario

class InventarioData {
    companion object {
        val inventarios: MutableList<Inventario> = mutableListOf(
            Inventario(1, mutableMapOf("El Principito" to 10, "Cien años de soledad" to 5))
        )
    }
}
