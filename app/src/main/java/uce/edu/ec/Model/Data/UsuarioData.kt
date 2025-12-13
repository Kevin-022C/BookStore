package uce.edu.ec.Model.Data

import uce.edu.ec.Model.Entity.Usuario

class UsuarioData {
    companion object {
        val usuarios: MutableList<Usuario> = mutableListOf(
            Usuario(1, "usera1", "kevin", "celi", "admin@bookstore.com", "admin123", "ADMIN", ""),
            Usuario(2, "user1", "Dylan", "Lema", "dilan@mail.com", "1234", "ADMIN", "Efectivo"),
            Usuario(3, "user2", "Jhonny", "Ninabanda", "jhonny@mail.com", "1235", "CLIENTE", "Efectivo"),
            Usuario(4, "user3", "Luis", "Perenguez", "luis@mail.com", "1236", "CLIENTE", "Targeta"),
            Usuario(5, "user4", "Diego", "Casagallo", "diego@mail.com", "1237", "CLIENTE", "Targeta")
        )
    }
}
