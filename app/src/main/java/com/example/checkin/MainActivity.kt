package com.example.checkin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.checkin.navigation.AppNavGraph
import com.example.checkin.ui.theme.MapeamentoDeRiscosTheme // <-- Substitua pelo nome do seu tema

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Opcional: Para o app usar a tela inteira (atrás das barras de status e navegação)
        setContent {
            // 1. Carrega o tema principal do seu App
            MapeamentoDeRiscosTheme { // <-- Use o nome do seu tema aqui
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 2. Cria o NavController, que gerencia a navegação
                    val navController = rememberNavController()

                    // 3. Chama o seu gráfico de navegação, que é o ponto de partida da sua UI
                    AppNavGraph(navController = navController)
                }
            }
        }
    }
}