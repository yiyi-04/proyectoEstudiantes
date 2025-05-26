
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import components.FormularioEstudiante
import components.ListaEstudiantes
import models.Estudiante

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Gestión de Estudiantes") {
        MaterialTheme {
            var estudiantes by remember { mutableStateOf(listOf<Estudiante>()) }
            var mostrarFormulario by remember { mutableStateOf(false) }
            var estudianteEditar by remember { mutableStateOf<Estudiante?>(null) }

            if (mostrarFormulario) {
                FormularioEstudiante(
                    estudiante = estudianteEditar,
                    onGuardar = { estudiante ->
                        if (estudianteEditar == null) {
                            estudiantes = estudiantes + estudiante
                        } else {
                            estudiantes = estudiantes.map {
                                if (it.id == estudiante.id) estudiante else it
                            }
                        }
                        mostrarFormulario = false
                        estudianteEditar = null
                    },
                    onCancelar = {
                        mostrarFormulario = false
                        estudianteEditar = null
                    }
                )
            } else {
                ListaEstudiantes(
                    estudiantes = estudiantes,
                    onAgregar = { mostrarFormulario = true },
                    onEditar = { estudiante ->
                        estudianteEditar = estudiante
                        mostrarFormulario = true
                    },
                    onEliminar = { id ->
                        estudiantes = estudiantes.filter { it.id != id }
                    }
                )
            }
        }
    }
}