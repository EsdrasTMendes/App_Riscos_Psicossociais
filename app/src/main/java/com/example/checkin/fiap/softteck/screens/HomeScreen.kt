package com.example.checkin.fiap.softteck.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.checkin.R
import com.example.checkin.fiap.softteck.components.BottomNavigation.Companion.BottomNavigationBar
import com.example.checkin.fiap.softteck.components.GreetingHeader.Companion.GreetingHeaderSection
import com.example.checkin.fiap.softteck.components.ReadingCard.Companion.ReadingCardSection

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.background_color))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GreetingHeaderSection(
                icon = painterResource(R.drawable.profile),
                title = stringResource(R.string.hello),
                subtitle = stringResource(R.string.how_are_you)
            )

            Row(
                modifier = Modifier.padding(20.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(R.string.reading_for_you),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = colorResource(R.color.light_green),
                    textAlign = TextAlign.Start
                )
            }

            Row {
                ReadingCardSection(
                    color = CardDefaults.cardColors(containerColor = colorResource(R.color.dark_blue)),
                    title = stringResource(R.string.description_internet),
                    buttonTitle = stringResource(R.string.more),
                    colorButton = ButtonDefaults.buttonColors(colorResource(R.color.light_green))
                )

                Spacer(modifier = Modifier.width(10.dp))

                ReadingCardSection(
                    color = CardDefaults.cardColors(containerColor = colorResource(R.color.dark_blue)),
                    title = stringResource(R.string.description_health),
                    buttonTitle = stringResource(R.string.more),
                    colorButton = ButtonDefaults.buttonColors(colorResource(R.color.background_profile))
                )

                Spacer(modifier = Modifier.width(10.dp))

                ReadingCardSection(
                    color = CardDefaults.cardColors(containerColor = colorResource(R.color.dark_blue)),
                    title = stringResource(R.string.description_internet),
                    buttonTitle = stringResource(R.string.more),
                    colorButton = ButtonDefaults.buttonColors(colorResource(R.color.light_green))
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.light_green)),
                modifier = Modifier.size(300.dp, height = 300.dp)
            ) {
                Column(modifier = Modifier.padding(top = 10.dp, start = 15.dp)) {
                    Text(
                        text = stringResource(R.string.how_are_you),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.background_profile)
                    )
                    Text(
                        text = stringResource(R.string.things_today),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.dark_blue)
                    )
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        Image(
                            painter = painterResource(R.drawable.bob),
                            contentDescription = stringResource(R.string.profile_user),
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .clickable {
                                    navController.navigate("TelaSentimentos")
                                }
                        )
                        Image(
                            painter = painterResource(R.drawable.nice),
                            contentDescription = stringResource(R.string.profile_user),
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .clickable {
                                    navController.navigate("TelaSentimentos")
                                }
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        Image(
                            painter = painterResource(R.drawable.smile),
                            contentDescription = stringResource(R.string.profile_user),
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .clickable {
                                    navController.navigate("TelaSentimentos")
                                }
                        )
                        Image(
                            painter = painterResource(R.drawable.shrek),
                            contentDescription = stringResource(R.string.profile_user),
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .clickable {
                                    navController.navigate("TelaSentimentos")
                                }
                        )
                    }
                }
            }
        }

        BottomNavigationBar(
            iconLeft = painterResource(R.drawable.operator),
            takeCare = stringResource(R.string.take_care),
            iconMiddle = painterResource(R.drawable.house),
            iconRight = painterResource(R.drawable.chat),
            privateChat = stringResource(R.string.private_chat),
            onLeftClick = { navController.navigate("helpStay") },
            onMidClick = { navController.navigate("HomeScreen") },
            onRightClick = { navController.navigate("HelpForumScreen") }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenView() {
    val fakeNavController = rememberNavController()
    HomeScreen(navController = fakeNavController)
}

