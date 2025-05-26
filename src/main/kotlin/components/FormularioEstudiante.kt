 package components

 import androidx.compose.foundation.layout.Arrangement
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.Row
 import androidx.compose.foundation.layout.Box
 import androidx.compose.foundation.layout.Spacer
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.foundation.layout.fillMaxWidth
 import androidx.compose.foundation.layout.padding
 import androidx.compose.foundation.layout.width
 import androidx.compose.material3.*
 import androidx.compose.runtime.Composable
 import androidx.compose.runtime.getValue
 import androidx.compose.runtime.mutableStateOf
 import androidx.compose.runtime.remember
 import androidx.compose.runtime.setValue
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.unit.dp
 import models.Estudiante

 @OptIn(ExperimentalMaterial3Api::class)
 @Composable
 fun FormularioEstudiante(
     estudiante: Estudiante?,
     onGuardar: (Estudiante) -> Unit,
     onCancelar: () -> Unit,
     modifier: Modifier = Modifier
 ) {
     var nombre by remember { mutableStateOf(estudiante?.nombre ?: "") }
     var carnet by remember { mutableStateOf(estudiante?.carnet ?: "") }
     var carrera by remember { mutableStateOf(estudiante?.carrera ?: "") }

     Box(
         modifier = Modifier.fillMaxSize(),
         contentAlignment = Alignment.Center
     ) {
         Column(
             modifier = Modifier
                 .width(500.dp)
                 .padding(16.dp),
             verticalArrangement = Arrangement.spacedBy(16.dp)
         ) {
             Text(
                 text = if (estudiante == null) "Agregar Estudiante" else "Editar Estudiante",
                 style = MaterialTheme.typography.headlineMedium
             )

             OutlinedTextField(
                 value = nombre,
                 onValueChange = { nombre = it },
                 label = { Text("Nombre") },
                 modifier = Modifier.fillMaxWidth()
             )

             OutlinedTextField(
                 value = carnet,
                 onValueChange = { carnet = it },
                 label = { Text("Carnet") },
                 modifier = Modifier.fillMaxWidth()
             )

             OutlinedTextField(
                 value = carrera,
                 onValueChange = { carrera = it },
                 label = { Text("Carrera") },
                 modifier = Modifier.fillMaxWidth()
             )

             Row(
                 modifier = Modifier.fillMaxWidth(),
                 horizontalArrangement = Arrangement.End,
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Button(
                     onClick = onCancelar,
                     colors = ButtonDefaults.buttonColors(
                         containerColor = MaterialTheme.colorScheme.errorContainer,
                         contentColor = MaterialTheme.colorScheme.onErrorContainer
                     )
                 ) {
                     Text("Cancelar")
                 }

                 Spacer(Modifier.width(16.dp))

                 Button(
                     onClick = {
                         val nuevoEstudiante = Estudiante(
                             id = estudiante?.id ?: (0..Int.MAX_VALUE).random(),
                             nombre = nombre,
                             carnet = carnet,
                             carrera = carrera
                         )
                         onGuardar(nuevoEstudiante)
                     }
                 ) {
                     Text(if (estudiante == null) "Agregar" else "Guardar Cambios")
                 }
             }
         }
     }
 }
