package com.example.examine_ai.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerComponent( selectedDate: Date) {
    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState()
    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                Button(onClick = { showDatePickerDialog = false }) {
                    Text(text = "Escolher data")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }
    TextField(
        value = selectedDate.toString(),
        onValueChange = { },
        Modifier
            .padding(8.dp)
            .background(Color.White)
            .onFocusChanged {
                if (it.isFocused) {
                    showDatePickerDialog = true
                }
            },
        label = {
            Text("Data do Exame")
        },
        readOnly = true
    )
}
