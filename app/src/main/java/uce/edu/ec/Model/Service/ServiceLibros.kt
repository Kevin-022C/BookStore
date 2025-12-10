package uce.edu.ec.Model.Service

import uce.edu.ec.Model.Entity.Libros
import uce.edu.ec.Model.Repository.IreLibros
import uce.edu.ec.Model.Data.LibrosData

class ServiceLibros : IreLibros {

    override fun save(libros: Libros): Libros {
        LibrosData.libros.add(libros)
        return libros
    }

    override fun update(libros: Libros): Libros {
        val index = LibrosData.libros.indexOfFirst { it.id == libros.id }
        if (index != -1) {
            LibrosData.libros[index] = libros
            return libros
        } else {
             throw Exception("Libro no encontrado para actualizar")
        }
    }

    override fun delete(libros: Libros) {
        val index = LibrosData.libros.indexOfFirst { it.id == libros.id }
        if (index != -1) {
            LibrosData.libros.removeAt(index)
        }
    }

    override fun obtenerLibroPorId(id: Int): Libros? {
        return LibrosData.libros.find { it.id == id }
    }
}
