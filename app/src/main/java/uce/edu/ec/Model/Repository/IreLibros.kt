package uce.edu.ec.Model.Repository

import uce.edu.ec.Model.Entity.Libros

interface IreLibros {
    fun save(libros: Libros): Libros
    fun update(libros: Libros): Libros
    fun delete(libros: Libros)
    fun obtenerLibroPorId(id: Int): Libros?

}