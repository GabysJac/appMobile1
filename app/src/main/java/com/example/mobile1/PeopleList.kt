package com.example.mobile1

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.util.UUID

@Composable
fun PersonList() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    var people by remember { mutableStateOf(listOf<Person>()) }
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                value = name,
                placeholder = { Text("Ingrese nombre") },
                onValueChange = { name = it },
                modifier = Modifier.weight(1f)
            )



        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            TextField(
                value = age,
                placeholder = { Text("Edad") },
                onValueChange = { age = it },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                if (name.isNotBlank() && age.isNotBlank() && age.toIntOrNull() != null) {
                    val newPerson = Person(UUID.randomUUID(), name, age.toInt())
                    people = people + newPerson
                    name = ""
                    age = ""
                } else {
                    Toast.makeText(context, "Ingrese datos válidos", Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Icono de añadir")
            }
        }
        LazyColumn {
            items(people) { person ->
                Text(
                    text = "ID: ${person.id}\nNombre: ${person.name}, Edad: ${person.age}",
                    modifier = Modifier.padding(16.dp)
                )
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonListPreview() {
    PersonList()
}
