package com.example.checkin.fiap.softteck.screens

import android.os.Message
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.checkin.fiap.softteck.components.*
import com.example.checkin.fiap.softteck.components.BottomNavigation.Companion.BottomNavigationBar
import com.example.checkin.fiap.softteck.components.Button.Companion.ClickButton
import com.example.checkin.fiap.softteck.components.Header.Companion.HeaderSection
import com.example.checkin.fiap.softteck.components.TextField.Companion.TextFieldInput


@Composable
fun HelpForumScreen(navController: NavController) {
    val messages = remember { mutableStateListOf<Message>() }
    var inputText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.background_color))
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            HeaderSection(
                title = "Papo",
                subtitle = "Particular",
                icon = painterResource(R.drawable.chat)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = colorResource(R.color.background_color)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Este é um espaço anônimo para apoio e \nescuta respeitosa.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.background_profile),
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = colorResource(R.color.background_color)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Lembre-se: aqui não substituímos ajuda \nprofissional.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.light_green),
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldInput(
                    phrase = inputText,
                    onValueChange = { inputText = it }
                )

                ClickButton(
                    containerColor = colorResource(id = R.color.background_profile),
                    title = "Postar",
                    onClick = {
                        if (inputText.isNotBlank()) {
                            inputText = ""
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .heightIn(max = 500.dp),
                    reverseLayout = true
                ) {

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

@Preview(showSystemUi = true)
@Composable
private fun HelpForumScreenView() {
    val fakeNavController = rememberNavController()
    HelpForumScreen(navController = fakeNavController)
}
