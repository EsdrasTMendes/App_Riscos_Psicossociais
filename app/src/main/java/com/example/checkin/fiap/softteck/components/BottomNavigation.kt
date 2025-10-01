package com.example.checkin.fiap.softteck.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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

class BottomNavigation {

    companion object {
        @Composable
        fun BottomNavigationBar(
            iconLeft: Painter,
            takeCare: String,
            iconMiddle: Painter,
            iconRight: Painter,
            privateChat: String,
            onLeftClick: () -> Unit,
            onMidClick: () -> Unit,
            onRightClick: () -> Unit,
            modifier: Modifier = Modifier
        ) {

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Card(
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    colors = CardDefaults.cardColors(colorResource(R.color.background_profile)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(7.dp)
                    ) {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = iconLeft,
                                contentDescription = "Icon Operator",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clickable { onLeftClick() }
                            )

                            Text(
                                text = takeCare,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        }

                        Column(
                            modifier = Modifier.padding(start = 20.dp)
                        ) {
                            Image(
                                painter = iconMiddle,
                                contentDescription = "Icon House",
                                modifier = Modifier
                                    .size(80.dp)
                                    .clickable { onMidClick() }
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = iconRight,
                                contentDescription = "Icon Operator",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clickable { onRightClick() }
                            )

                            Text(
                                text = privateChat,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
