 package components

 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.Spacer
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.foundation.layout.fillMaxWidth
 import androidx.compose.foundation.layout.padding
 import androidx.compose.foundation.layout.width
 import androidx.compose.foundation.lazy.LazyColumn
 import androidx.compose.foundation.lazy.items
 import androidx.compose.material.icons.Icons
 import androidx.compose.material.icons.filled.Add
 import androidx.compose.material3.Button
 import androidx.compose.material3.Icon
 import androidx.compose.material3.MaterialTheme
 import androidx.compose.material3.Text
 import androidx.compose.runtime.Composable
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.text.style.TextAlign
 import androidx.compose.ui.unit.dp
 import models.Estudiante
 import components.TarjetaEstudiante

@Composable
fun ListaEstudiantes(
    estudiantes: List<Estudiante>,
    onAgregar: () -> Unit,
    onEditar: (Estudiante) -> Unit,
    onEliminar: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Lista de Estudiantes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp))

                    Button(
                    onClick = onAgregar,
            modifier = Modifier.align(Alignment.End).padding(bottom = 16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Agregar")
            Spacer(Modifier.width(8.dp))
            Text("Agregar Estudiante")
        }

        if (estudiantes.isEmpty()) {
            Text(
                "No hay estudiantes registrados",
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(estudiantes) { estudiante ->
                    TarjetaEstudiante(
                        estudiante = estudiante,
                        onEditar = { onEditar(estudiante) },
                        onEliminar = { onEliminar(estudiante.id) },
                        modifier = Modifier.fillParentMaxWidth().padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}

