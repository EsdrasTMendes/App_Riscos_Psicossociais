package com.example.checkin.fiap.softteck.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkin.R

class Button {

    companion object {

        @Composable
        fun ClickButton(
            containerColor: Color,
            title: String,
            onClick: () -> Unit
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, end = 35.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onClick,
                    modifier = Modifier
                        .size(width = 80.dp, height = 30.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = containerColor
                    ),
                    contentPadding = PaddingValues()
                ) {
                    Text(
                        text = title
                    )
                }
            }
        }

        @Composable
        fun ButtonToRepost(
            containerColor: Color,
            painter: Painter,
            title: String
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 15.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .size(width = 100.dp, height = 25.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = containerColor
                    ),
                    contentPadding = PaddingValues()
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = title,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        @Composable
        fun ButtonIUnderstand(
            color: Color,
            title: String,
            onClick: () -> Unit
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier
                    .size(width = 280.dp, height = 50.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = color
                )
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.light_green)
                )
            }
        }
    }
}