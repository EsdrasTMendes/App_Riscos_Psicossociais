package com.example.checkin.fiap.softteck.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkin.R

class EncouragementCard {

    companion object {

        @Composable
        fun EncouragementCardSection(
            title: String,
            subtitle: String,
            icon: Painter,
            onClose: () -> Unit
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column() {
                    Text(
                        text = title,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 28.sp,
                        color = colorResource(id = R.color.dark_blue)
                    )
                    Text(
                        text = subtitle,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp,
                        color = colorResource(id = R.color.light_green)
                    )
                }

                Row(
                    modifier = Modifier.padding(top = 10.dp, start = 70.dp)
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = "Icon Close",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onClose() }
                    )
                }

            }
        }
    }
}