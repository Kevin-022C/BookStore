package uce.edu.ec.Model.Data

import uce.edu.ec.Model.Entity.Usuario

class UsuarioData {
    companion object {
        val usuarios: MutableList<Usuario> = mutableListOf(
            Usuario(1, "admin", "kevin", "celi", "admin@bookstore.com", "admin123", "ADMIN", "Tarjeta"),
            Usuario(2, "user1", "Juan", "Perez", "juan@mail.com", "1234", "CLIENTE", "Efectivo")
        )
    }
}
