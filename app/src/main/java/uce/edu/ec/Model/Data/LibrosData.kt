package uce.edu.ec.Model.Data

import uce.edu.ec.Model.Entity.Libros

class LibrosData {
    // Lista estática para simular persistencia en memoria durante la ejecución de la app
    companion object {
        val libros: MutableList<Libros> = mutableListOf(
            Libros(1, "El Principito", "Antoine de Saint-Exupéry", 15.50, "Un clásico de la literatura.", "1991","imagen_principito"),
            Libros(2, "Cien años de soledad", "Gabriel García Márquez", 20.00, "Obra maestra del realismo mágico.","1950" ,"imagen_cien_anios"),
            Libros(3, "1984", "George Orwell", 18.00, "Novela distópica.", "1990","imagen_1984"),
            Libros(4, "Harry Potter y la piedra filosofal", "J.K. Rowling", 25.00, "El inicio de una saga mágica.","1980" ,"imagen_hp1")
        )
    }
}
