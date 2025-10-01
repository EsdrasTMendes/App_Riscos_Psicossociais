package com.example.checkin.fiap.softteck.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkin.R

class Header {

    companion object {

        @Composable
        fun HeaderSection(
            title: String,
            subtitle: String,
            icon: Painter,
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.dark_blue)),
                    modifier = Modifier.size(350.dp, 110.dp),
                    shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(bottom = 15.dp)
                        ) {
                            Text(
                                text = title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp,
                                color = colorResource(id = R.color.background_profile)
                            )
                            Text(
                                text = subtitle,
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp,
                                color = colorResource(id = R.color.light_green)
                            )
                        }
                        Image(
                            painter = icon,
                            contentDescription = "Icon chat",
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
            }
        }
    }
}