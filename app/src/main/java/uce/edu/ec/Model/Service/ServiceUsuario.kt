package uce.edu.ec.Model.Service

import uce.edu.ec.Model.Entity.Usuario
import uce.edu.ec.Model.Repository.IreUsuario
import uce.edu.ec.Model.Data.UsuarioData

class ServiceUsuario : IreUsuario {

    override fun save(usuario: Usuario): Usuario {
        UsuarioData.usuarios.add(usuario)
        return usuario
    }

    override fun update(usuario: Usuario): Usuario {
        val index = UsuarioData.usuarios.indexOfFirst { it.id == usuario.id }
        if (index != -1) {
            UsuarioData.usuarios[index] = usuario
            return usuario
        } else {
            throw Exception("Usuario no encontrado para actualizar")
        }
    }

    override fun delete(usuario: Usuario) {
        val index = UsuarioData.usuarios.indexOfFirst { it.id == usuario.id }
        if (index != -1) {
            UsuarioData.usuarios.removeAt(index)
        }
    }

    override fun obtenerUsuarioPorId(id: Int): Usuario? {
        return UsuarioData.usuarios.find { it.id == id }
    }

    override fun obtenerUsuarioPorNombreUsuario(nombreUsuario: String): Usuario? {
        return UsuarioData.usuarios.find { it.nombreUsuario == nombreUsuario }
    }

    override fun obtenerUsuarioPorNombreYApellido(nombre: String, apellido: String): Usuario? {
        return UsuarioData.usuarios.find { 
            it.nombre.equals(nombre, ignoreCase = true) && 
            it.apellido.equals(apellido, ignoreCase = true) 
        }
    }
}
