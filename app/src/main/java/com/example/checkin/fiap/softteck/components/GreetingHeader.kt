package com.example.checkin.fiap.softteck.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkin.R

class GreetingHeader {

    companion object {

        @Composable
        fun GreetingHeaderSection(
            icon: Painter,
            title: String,
            subtitle: String
        ) {

            Row(
                modifier = Modifier
                    .padding(top = 40.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.background_profile)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(width = 300.dp, height = 65.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = icon,
                            contentDescription = stringResource(R.string.profile_user),
                            modifier = Modifier.size(50.dp)
                        )

                        Spacer(modifier = Modifier.width(30.dp))

                        Column() {
                            Text(
                                text = title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = colorResource(id = R.color.background_color)
                            )
                            Text(
                                text = subtitle,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = colorResource(id = R.color.light_green)
                            )
                        }
                    }
                }
            }
        }
    }
}
