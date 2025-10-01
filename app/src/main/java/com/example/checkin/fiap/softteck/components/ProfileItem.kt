package com.example.checkin.fiap.softteck.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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

class ProfileItem {

    companion object {

        @Composable
        fun ProfileGirl(
            color: CardColors,
            title: String,
            painter: Painter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Icon Girl",
                    modifier = Modifier.size(40.dp)
                )

                Card(
                    modifier = Modifier
                        .size(width = 80.dp, height = 30.dp)
                        .padding(start = 10.dp),
                    colors = color
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = colorResource(id = R.color.light_green)
                        )
                    }
                }
            }
        }


        @Composable
        fun ProfileMan(
            color: CardColors,
            title: String,
            painter: Painter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, end = 20.dp),
                horizontalArrangement = Arrangement.End,

                ) {
                Card(
                    modifier = Modifier
                        .size(width = 80.dp, height = 30.dp)
                        .padding(end = 10.dp),
                    colors = color
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = colorResource(id = R.color.light_green)
                        )
                    }
                }

                Image(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }

}