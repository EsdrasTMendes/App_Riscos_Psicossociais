package com.example.checkin.fiap.softteck.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.checkin.R

class TextField {

    companion object {

        @Composable
        fun TextFieldInput(
            phrase: String,
            onValueChange: (String) -> Unit
        ) {
            TextField(
                modifier = Modifier.size(width = 320.dp, height = 100.dp),
                shape = RoundedCornerShape(10.dp),
                value = phrase,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = "Sinta-se a vontade...",
                        color = colorResource(id = R.color.white),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF88D499),
                    unfocusedContainerColor = Color(0xFF88D499),
                    cursorColor = Color.Green,
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.DarkGray,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Gray
                )
            )
        }

        @Composable
        fun TextFieldWantToHelp(
            title: String
        ) {
            TextField(
                modifier = Modifier
                    .size(width = 320.dp, height = 80.dp)
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(10.dp),
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = title
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF88D499),
                    unfocusedContainerColor = Color(0xFF88D499),
                    cursorColor = Color.Green,
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.DarkGray,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Gray
                )
            )
        }

        @Composable
        fun ReplyTextField(
            value: String,
            onValueChange: (String) -> Unit,
            modifier: Modifier = Modifier
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text("Digite a resposta...") },
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F5F5),
                    unfocusedContainerColor = Color(0xFFF5F5F5),
                    cursorColor = Color.Green,
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.DarkGray,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Gray
                )
            )
        }

    }
}