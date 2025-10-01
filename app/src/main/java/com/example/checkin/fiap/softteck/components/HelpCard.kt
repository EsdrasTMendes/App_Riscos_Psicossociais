package com.example.checkin.fiap.softteck.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkin.R

class HelpCard {

    companion object {

        @Composable
        fun HelpOptionManagerContact(
            icon: Painter,
            title: String,
            buttonTitle: String
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = icon,
                    contentDescription = "Icon chat",
                    modifier = Modifier.size(100.dp)
                )

                Column() {
                    Text(
                        text = title,
                        color = colorResource(id = R.color.light_green),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )

                    Row(
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        Button(
                            onClick = {},
                            modifier = Modifier.size(width = 115.dp, height = 30.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.dark_blue)
                            ),
                            contentPadding = PaddingValues()
                        ) {
                            Text(
                                text = buttonTitle,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}