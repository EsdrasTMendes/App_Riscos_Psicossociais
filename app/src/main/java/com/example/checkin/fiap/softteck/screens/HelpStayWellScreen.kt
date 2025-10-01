package com.example.checkin.fiap.softteck.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.checkin.R
import com.example.checkin.fiap.softteck.components.BottomNavigation.Companion.BottomNavigationBar
import com.example.checkin.fiap.softteck.components.Card.Companion.ModalHelpStayWellCard
import com.example.checkin.fiap.softteck.components.GreetingHeader.Companion.GreetingHeaderSection

@Composable
fun HelpStayWellScreen(navController: NavController) {

    var showModal by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.background_color))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            GreetingHeaderSection(
                icon = painterResource(R.drawable.profile),
                title = stringResource(R.string.hello),
                subtitle = stringResource(R.string.how_are_you)
            )
        }

        if (!showModal) {
            BottomNavigationBar(
                iconLeft = painterResource(R.drawable.operator),
                takeCare = stringResource(R.string.take_care),
                iconMiddle = painterResource(R.drawable.house),
                iconRight = painterResource(R.drawable.chat),
                privateChat = stringResource(R.string.private_chat),
                onLeftClick = { navController.navigate("helpStay") },
                onMidClick = { navController.navigate("home") },
                onRightClick = { navController.navigate("forum") }
            )
        }

    }

    if (showModal) {
        ModalHelpStayWellCard(onDismiss = { navController.popBackStack() })
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HelpStayWellView() {
    val fakeNavController = rememberNavController()
    HelpStayWellScreen(navController = fakeNavController)
}