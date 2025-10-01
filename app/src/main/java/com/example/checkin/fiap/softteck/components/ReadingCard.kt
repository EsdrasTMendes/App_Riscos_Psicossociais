package com.example.checkin.fiap.softteck.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkin.R

class ReadingCard {

    companion object {

        @Composable
        fun ReadingCardSection(
            color: CardColors,
            title: String,
            buttonTitle: String,
            colorButton: ButtonColors
        ) {
            Card(
                colors = color,
                modifier = Modifier
                    .size(120.dp, 120.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.light_green),
                        fontSize = 15.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Button(
                    onClick = { },
                    modifier = Modifier
                        .size(width = 110.dp, height = 30.dp)
                        .fillMaxSize(),
                    colors = colorButton,
                    contentPadding = PaddingValues()
                ) {

                    Text(
                        textAlign = TextAlign.Center,
                        text = buttonTitle,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
//                            Image(
//                                painter = painterResource(R.drawable.plus),
//                                contentDescription = stringResource(R.string.profile_user),
//                                modifier = Modifier.size(30.dp)
//                            )
                }
            }
        }
    }
}