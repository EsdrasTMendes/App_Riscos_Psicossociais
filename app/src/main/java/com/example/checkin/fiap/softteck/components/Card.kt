package com.example.checkin.fiap.softteck.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkin.R
import com.example.checkin.fiap.softteck.components.Button.Companion.ButtonIUnderstand
import com.example.checkin.fiap.softteck.components.EncouragementCard.Companion.EncouragementCardSection
import com.example.checkin.fiap.softteck.components.HelpCard.Companion.HelpOptionManagerContact

class Card {

    companion object {

        @Composable
        fun CardInfo(
            color: CardColors,
            title: String,
            colorText: Color
        ) {
            Card(
                colors = color,
                modifier = Modifier.size(280.dp, 50.dp),
                shape = RoundedCornerShape(15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        color = colorText,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        @Composable
        fun ModalCard(onDismiss: () -> Unit, onNavigate: () -> Unit) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.background_profile)),
                    modifier = Modifier
                        .width(320.dp)
                        .heightIn(min = 600.dp, max = 600.dp),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.padding(top = 20.dp)
                        ) {
                            Column() {
                                Row() {
                                    Text(
                                        text = "Primeiro",
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 34.sp,
                                        color = colorResource(id = R.color.background_color)
                                    )

                                    Row(
                                        modifier = Modifier.padding(start = 60.dp),
                                        horizontalArrangement = Arrangement.End
                                    ) {

                                        Image(
                                            painter = painterResource(R.drawable.warning),
                                            contentDescription = "Icon Warning",
                                            modifier = Modifier.size(45.dp)
                                        )

                                        Row(
                                            modifier = Modifier
                                                .padding(start = 15.dp)
                                                .clickable { onDismiss() }
                                        ) {
                                            Icon(
                                                modifier = Modifier.size(25.dp),
                                                painter = painterResource(id = R.drawable.close),
                                                contentDescription = "Icon Close"
                                            )
                                        }
                                    }
                                }

                                Text(
                                    text = "preste atenção!",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 34.sp,
                                    color = colorResource(id = R.color.light_green)
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.padding(15.dp)
                        ) {
                            Text(
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth(),
                                text = buildAnnotatedString {
                                    append("\t\tEste fórum é um ")
                                    withStyle(SpanStyle(color = colorResource(id = R.color.light_green))) {
                                        append("espaço seguro ")
                                    }
                                    append(
                                        "\npara a troca de apoio emocional \nentre usuários. No entanto é" +
                                                "\nfundamental que você "
                                    )
                                    withStyle(SpanStyle(color = colorResource(id = R.color.light_green))) {
                                        append("esteja ciente ")
                                    }
                                    append("das ")
                                    withStyle(SpanStyle(color = colorResource(id = R.color.light_green))) {
                                        append("seguintes diretrizes: ")
                                    }
                                },
                                fontSize = 18.sp,
                                color = colorResource(id = R.color.black),
                                fontWeight = FontWeight.Medium
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        CardInfo(
                            color = CardDefaults.cardColors(containerColor = colorResource(R.color.light_green)),
                            title = "● Não oferecemos diagnósticos ou tratamentos psicológicos.",
                            colorText = colorResource(id = R.color.black)
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        CardInfo(
                            color = CardDefaults.cardColors(containerColor = colorResource(R.color.dark_blue)),
                            title = "● Privacidade e anonimato são \nprivacidades.",
                            colorText = colorResource(id = R.color.light_green)
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        CardInfo(
                            color = CardDefaults.cardColors(containerColor = colorResource(R.color.light_green)),
                            title = "● Moderação ativa e qualificação.",
                            colorText = colorResource(id = R.color.black)
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        CardInfo(
                            color = CardDefaults.cardColors(containerColor = colorResource(R.color.dark_blue)),
                            title = "● Responsabilidade sobre interação.",
                            colorText = colorResource(id = R.color.light_green)
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        ButtonIUnderstand(
                            color = colorResource(id = R.color.dark_blue),
                            title = "Eu entendo",
                            onClick = onNavigate
                        )

                    }
                }
            }
        }

        @Composable
        fun ModalHelpStayWellCard(onDismiss: () -> Unit) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Card(
                    modifier = Modifier
                        .size(width = 420.dp, height = 700.dp),
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.background_profile))
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        EncouragementCardSection(
                            title = "Fique bem,",
                            subtitle = "buscar ajuda é um ato \nde coragem.",
                            icon = painterResource(id = R.drawable.close),
                            onClose = { onDismiss() }
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        HelpOptionManagerContact(
                            icon = painterResource(R.drawable.operator),
                            title = "Entre em contato com seu gerente de \npessoas se você precisa " +
                                    "de ajuda com \nassuntos relacionados ao ambiente de \ntrabalho.",
                            buttonTitle = stringResource(R.string.click_here)
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        HelpOptionManagerContact(
                            icon = painterResource(R.drawable.location),
                            title = "Encontre o apoio que você merece: \nbusque ajuda profissional e" +
                                    "conecte-se \ncom o psicólogo ideal para você.",
                            buttonTitle = stringResource(R.string.click_here)
                        )

                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Image(
                                painter = painterResource(R.drawable.conversation),
                                contentDescription = "Icon Conversation",
                                modifier = Modifier.size(350.dp)
                            )
                        }

                    }
                }
            }
        }
    }
}