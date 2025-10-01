package com.example.checkin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.checkin.fiap.softteck.screens.*
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.checkin.fiap.softteck.data.EmpresaRepository

@Composable
fun AppNavGraph(navController: NavHostController, context: Context) {
    val repo = EmpresaRepository(context)

    NavHost(
        navController = navController,
        startDestination = "Login"
    ) {
        // Tela de Login
        composable("Login") {
            var loginError by remember { mutableStateOf(false) }

            LoginScreen(
                onLoginClick = { email, senha ->
                    repo.login(email, senha) { token ->
                        if (token != null) {
                            navController.navigate("HomeScreen") {
                                popUpTo("Login") { inclusive = true }
                            }
                        } else {
                            loginError = true
                        }
                    }
                },
                showError = loginError,
                onDismissError = { loginError = false }
            )
        }


        // Tela principal após login
        composable("HomeScreen") {
            HomeScreen(navController = navController)
        }

        // Demais telas que você já tinha
        composable("perguntas") {
            AutoAvaliacao1(navController = navController)
        }
        composable("HelpForumScreen") {
            HelpForumScreen(navController = navController)
        }
        composable("helpStay") {
            HelpStayWellScreen(navController = navController)
        }
        composable("forumModalAlert") {
            ForumModalAlertScreen(navController = navController)
        }
        composable("TelaSentimentos") {
            TelaSentimentos(navController)
        }
    }
}
